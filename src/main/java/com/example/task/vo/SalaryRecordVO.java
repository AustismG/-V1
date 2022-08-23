package com.example.task.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 薪酬记录视图对象
 */
@Data
public class SalaryRecordVO {
    /**
     * 薪酬记录ID
     */
    private Long recordId;

    /**
     * 员工ID
     */
    private Long eeId;

    /**
     * 岗位工资
     */
    private BigDecimal postPay;

    /**
     * 绩效工资
     */
    private BigDecimal performancePay;

    /**
     * 工龄工资
     */
    private BigDecimal standingPay;

    /**
     * 津贴补助
     */
    private BigDecimal allowance;

    /**
     * 个人所得税
     */
    private BigDecimal personalIncomeTax;
}
