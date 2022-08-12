package com.example.task.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 员工视图对象
 */
@Data
public class EmployeeVO {
    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不能为空")
    private Long eeId;

    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空")
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
    @NotBlank(message = "手机号不能为空")
    @Length(max = 11,message = "手机号长度最大为11")
    private String phone;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 30,message = "部门名称长度最大为30")
    private String departmentName;
}
