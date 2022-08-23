package com.example.task.param.DepParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 条件搜索部门参数
 */
@Data
public class SearchDepartmentParam {
    /**
     * 部门ID
     */
    @Max(value = 70000,message = "部门ID最大值为70000")
    @Min(value = 10000,message = "部门ID最小值为10000")
    private Long departmentId;

    /**
     * 父部门ID
     */
    @Max(value = 70000,message = "部门ID最大值为70000")
    @Min(value = 10000,message = "部门ID最小值为10000")
    private Long parentId;

    /**
     * 部门名称
     */
    @Length(max = 30,message = "部门名称长度最大为30")
    private String departmentName;
}
