package com.example.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 薪酬记录表
 * @TableName t_salary
 */
@Data
public class Salary implements Serializable {
    /**
     * 薪酬记录ID
     */
    private Long recordId;

    /**
     * 岗位工资
     */
    private Integer postPay;

    /**
     * 绩效工资
     */
    private Integer performancePay;

    /**
     * 工龄工资
     */
    private Integer standingPay;

    /**
     * 津贴补助
     */
    private Integer allowance;

    /**
     * 个人所得税
     */
    private Integer personalIncomeTax;

    /**
     * 员工ID
     */
    private Long eId;

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