package com.example.task.param.DepParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
     * 父部门名称
     */
    @Length(max = 30, message = "父部门名称长度最大为30")
    private String parentDepName;
}

