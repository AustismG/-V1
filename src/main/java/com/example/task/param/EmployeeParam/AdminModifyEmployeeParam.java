package com.example.task.param.EmployeeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 管理员修改员工信息参数
 */
@Data
public class AdminModifyEmployeeParam {
    /**
     * 登录密码
     */
    @Length(max = 20,message = "密码长度最大为20")
    private String password;

    /**
     * 员工姓名
     */
    @Length(max = 20,message = "姓名长度最大为20")
    private String employeeName;

    /**
     * 性别
     */
    @Min(value = 0,message = "性别最小为0")
    @Max(value = 2,message = "性别最大为2")
    private Integer sex;

    /**
     * 手机号
     */
    @Length(max = 11,message = "手机号长度最大为11")
    private String phone;

    /**
     * 部门名称
     */
    private Long departmentId;

    /**
     * 用户角色
     */
    @Max(value = 1,message = "角色最大值为1")
    @Min(value = 0,message = "角色最小值为0")
    private Integer role;
}
