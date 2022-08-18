package com.example.task.controller;

import com.example.task.entity.Employee;
import com.example.task.param.EmployeeParam.UpdatePasswordParam;
import com.example.task.param.LoginParam;
import com.example.task.param.RegisterParam;
import com.example.task.service.EmployeeService;
import com.example.task.utils.JWTUtil;
import com.example.task.param.EmployeeParam.EmployeeModifyOwnInfoParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/system/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * @Author Gzy
     * @Description 系统用户注册
     * @Param [registerParam]
     * @return void
     * @is_Available 测试已通过!       
     **/
    @PostMapping("/register")
    public void register(@RequestBody RegisterParam registerParam) {
        log.info("这是接收到的Body："+registerParam);
        employeeService.register(registerParam);
    }

    /**
     * @Author Gzy
     * @Description 系统用户登录
     * @Param [loginParam, httpServletResponse]
     * @return java.lang.Object
     * @is_Available 测试已通过!       
     **/
    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginParam loginParam, HttpServletResponse httpServletResponse) {
        Employee employee = employeeService.login(loginParam.getEmployeeId(), loginParam.getPassword());
        String token = JWTUtil.createToken(employee);
        httpServletResponse.setHeader("token", token);
        return new Object();
    }

    /**
     * @Author Gzy
     * @Description 员工修改自己的信息
     * @Param [employee, employeeEditVo]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PutMapping("/info")
    @PreAuthorize("hasRole('USER')")
    public void modifyOwnInfo(@AuthenticationPrincipal Employee employee,
                              @RequestBody @Valid EmployeeModifyOwnInfoParam employeeModifyOwnInfoParam) {
        employeeService.modifyOwnInfo(employee.getEmployeeId(), employeeModifyOwnInfoParam);
    }

    @PutMapping("/password/{employeeId}")
    public void updatePassword(@RequestBody UpdatePasswordParam updatePasswordParam, @PathVariable Long employeeId) {
        employeeService.updatePassword(updatePasswordParam.getOriginalPassword(),
                updatePasswordParam.getModifiedPassword(),
                employeeId);
    }
}

//普通员工token
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbXBsb3llZSI6eyJyb2xlIjoiMCIsImlkIjoiODAwMDEwIn0sImV4cCI6MTY1OTU5NTkyNX0.B4Sp3Jvq2N2Qx0y3buYM0RBm8UYSjj312Mxv2InmcKs