package com.example.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 部门表
 * @TableName t_department
 */
@Data
public class Department implements Serializable {
    /**
     * 部门ID
     */
    private Long depId;

    /**
     * 部门ID
     */
    private String depName;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}