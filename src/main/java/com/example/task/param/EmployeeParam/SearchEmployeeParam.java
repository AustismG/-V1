package com.example.task.param.EmployeeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 条件搜索员工参数
 */
@Data
public class SearchEmployeeParam {

    /**
     * 员工ID
     */
    @Min(value = 800000,message = "员工ID最小值为800000")
    @Max(value = 809999,message = "员工ID最大值为809999")
    private Long employeeId;

    /**
     * 员工姓名
     */
    @Length(max = 20,message = "员工姓名长度最大为20")
    private String employeeName;

    /**
     * 用户角色
     */
    @Min(value = 0,message = "角色值最小为0")
    @Max(value = 1,message = "角色值最大为1")
    private Integer role;

    /**
     * 手机号
     */
    @Length(max = 11,message = "手机号长度最大为11")
    private String phone;

    /**
     * 部门名称
     */
    private Long departmentId;
}
