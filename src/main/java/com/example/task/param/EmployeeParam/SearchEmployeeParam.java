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
    @Length(max = 6,min = 6,message = "员工ID长度为6位")
    private Long eeId;

    /**
     * 员工姓名
     */
    @Length(max = 20,message = "员工姓名长度最大为20")
    private String eeName;

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
    @Length(max = 30,message = "部门名称长度最大为30")
    private String departmentName;
}
