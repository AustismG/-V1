package com.example.task.param.EmployeeParam;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 员工修改密码参数
 */
@Data
public class UpdatePasswordParam {
    /**
     * 原密码
     **/
    @NotNull(message = "原密码不能为空")
    private String originalPassword;

    /**
     * 新密码
     **/
    @NotNull(message = "新密码不能为空")
    private String modifiedPassword;
}
