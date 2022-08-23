package com.example.task.param.DepParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 管理员修改部门信息参数
 */
@Data
public class AdminModifyDepartmentParam {
    /**
     * 部门名称
     */
    @Length(max = 30, message = "部门名称长度最大为30")
    private String departmentName;

    /**
     * 父部门ID
     */
    @Max(value = 70000,message = "部门ID最大值为70000")
    @Min(value = 10000,message = "部门ID最小值为10000")
    private Long parentDepId;
}

