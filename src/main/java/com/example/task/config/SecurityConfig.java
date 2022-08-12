package com.example.task.config;


import com.example.task.advice.GlobalExceptionHandler;
import com.example.task.entity.Employee;
import com.example.task.filter.TokenFilter;
import com.example.task.mapper.EmployeeMapper;
import com.example.task.vo.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这是SpringSecurity的配置类，一旦看到extends WebSecurityConfigurerAdapter就应该想到这是安全相关的配置
 * 这里继承的是WebSecurityConfigurerAdapter，
 */
@Configuration
@Import(BCryptPasswordEncoder.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenFilter tokenFilter;

    //认证失败处理器
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 下面这个方法用于配置认证方式(如何根据用户名获取用户信息,以及密码加密方式)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 用@Bean往容器中注入AuthenticationManager，这样UserServiceImpl才能Autowired它，不然会报错找不到这个类型的bean
     * 我们要用它来帮我们实现认证，如果认证失败会抛出异常
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 下面这个方法用于配置"保护策略"
     * 在不同的项目中，不拦截URL列表改通常是不同的
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers("/system/employees/login").permitAll()
                .antMatchers("/system/employees/register").permitAll()
                /*.antMatchers("/api/msgboard/v1/users/login").permitAll()
                .antMatchers("/api/msgboard/v1/admins/login").permitAll()*/
                .anyRequest().authenticated();

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
        //允许跨域
        http.cors();

    }

    /**
     * 这个类就是我们自定义如何去从数据库，根据用户名拿到用户信息的
     */
    @Service
    public static class UserDetailsServiceImpl implements UserDetailsService {
        @Autowired
        private EmployeeMapper employeeMapper;

        /**
         * 这步就是根据Controller层输入的username去数据库拿出加密之后的password，准确地说，它是把整个用户都拿出来了，封装成UserDetails
         * 至于Controller层输入的密码是否和数据库里保存地密码一样，这个判断是由SpringSecurity帮我们做的
         * 它会把Controller层输入的密码按照PasswordEncoder进行加密，然后去和数据库里的加密后的密码判断是否匹配
         * 如果不匹配就会抛异常。（抛异常也没事，我已经在UserServiceImpl的login方法里面用try-catch捕获了）
         */
        @Override
        public UserDetails loadUserByUsername(String username) {
            Long eeId = Long.valueOf(username);
            //通过ID获取完整的employee实体对象
            Employee employee = employeeMapper.getEmployeeByEId(eeId);
            if (employee == null) {
                throw new UsernameNotFoundException("员工ID为" + eeId + "的账户不存在");
            }
            return employee;
        }
    }

    /**
     * 认证失败处理器，专门负责处理在Filter中抛出的异常
     */
    @Component
    @Slf4j
    public static class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
        @Autowired
        private ObjectMapper objectMapper;

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
            //设置HTTP状态码为401，表示认证失败
            log.warn(GlobalExceptionHandler.formatException(authException, request, null, false));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(objectMapper.writeValueAsString(CommonResult.failure("[SpringSecurity] 认证失败，因为访问的接口需要token，但是token缺失/token无效/token过期")));
        }
    }
}