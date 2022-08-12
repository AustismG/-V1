package com.example.task.param.DepParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 条件搜索部门参数
 */
@Data
public class SearchDepartmentParam {
    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 部门名称
     */
    @Length(max = 30,message = "部门名称长度最大为30")
    private String departmentName;
}
