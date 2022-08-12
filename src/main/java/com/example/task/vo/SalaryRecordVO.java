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
    @NotNull(message = "员工ID不能为空")
    private Long eeId;

    /**
     * 岗位工资
     */
    @NotNull(message = "岗位工资不能为空")
    private BigDecimal postPay;

    /**
     * 绩效工资
     */
    @NotNull(message = "绩效工资不能为空")
    private BigDecimal performancePay;

    /**
     * 工龄工资
     */
    @NotNull(message = "工龄工资不能为空")
    private BigDecimal standingPay;

    /**
     * 津贴补助
     */
    @NotNull(message = "津贴补助不能为空")
    private BigDecimal allowance;

    /**
     * 个人所得税
     */
    @NotNull(message = "个人所得税不能为空")
    private BigDecimal personalIncomeTax;
}
