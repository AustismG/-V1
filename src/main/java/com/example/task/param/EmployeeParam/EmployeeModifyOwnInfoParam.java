package com.example.task.param.EmployeeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 用户修改自己信息参数
 */
@Data
public class EmployeeModifyOwnInfoParam {
    /**
     * 登录密码
     */
    @Length(max = 20,message = "密码长度最大为20")
    private String password;

    /**
     * 员工姓名
     */
    @Length(max = 20,message = "员工姓名长度最大为20")
    private String eeName;

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

    @Length(max = 30,message = "部门名称长度最大为30")
    private String departmentName;
}
