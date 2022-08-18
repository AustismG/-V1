package com.example.task.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统用户登录参数
 */
@Data
public class LoginParam {
    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不能为空")
    private Long employeeId;

    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(max = 20,message = "密码长度最大为20")
    private String password;
}
