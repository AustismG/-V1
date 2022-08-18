package com.example.task.param.EmployeeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 管理员添加员工参数
 */
@Data
public class AdmInsertEmployeeParam {
    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不能为空")
    private Long employeeId;

    /**
     * 员工姓名
     */
    @NotBlank(message = "姓名不能为空")
    @Length(max = 20,message = "姓名长度最大为20")
    private String employeeName;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @Min(value = 0,message = "性别最小为0")
    @Max(value = 2,message = "性别最大为2")
    private Integer sex;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Length(max = 11,message = "手机号长度最大为11")
    private String phone;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空")
    private Long departmentId;

    /**
     * 用户角色
     */
    @NotNull(message = "用户角色不能为空")
    @Max(value = 1,message = "角色最大值为1")
    @Min(value = 0,message = "角色最小值为0")
    private Integer role;
}
