package com.example.task.param.DepParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加部门参数
 */
@Data
public class AdminAddDepParam {

    /**
     * 部门ID
     */
    @NotNull(message = "部门ID不能为空")
    private Long departmentId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 30, message = "部门名称长度最大为30")
    private String departmentName;

    /**
     * 父部门名称
     */
    @NotNull(message = "父部门名称不能为空")
    private Long parentDepId;
}

