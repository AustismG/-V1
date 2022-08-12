package com.example.task.filter;

import com.example.task.entity.Employee;
import com.example.task.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("=================请求进来了=================");
        log.info("[新请求]\n<方法>  {}\n<URI>  {}\n<Query>  {}\n<主机>  {}",
                request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getRemoteHost());
        String authorization = request.getHeader("authorization");

        //如果有token，就执行token解析，若解析成功，就设置Authenticated为true，表示认证成功
        if (StringUtils.hasText(authorization)) {
            Employee employee;
            try {
                employee = jwtUtil.getEmployeeFromToken(authorization);
            } catch (Exception e) {
                log.warn("在解析token时出错，错误原因:{}", e.getMessage());
                throw new BadCredentialsException(e.getMessage());
            }

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities())
            );

        }
        filterChain.doFilter(request, response);
        log.info("=================请求离开了=================");

    }
}