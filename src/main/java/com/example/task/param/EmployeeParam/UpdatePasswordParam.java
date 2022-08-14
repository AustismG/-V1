package com.example.task.param.EmployeeParam;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 */
@Data
public class UpdatePasswordParam {
    @NotNull(message = "原密码不能为空")
    private String originPassword;

    @NotNull(message = "新密码不能为空")
    private String modifiedPassword;
}
