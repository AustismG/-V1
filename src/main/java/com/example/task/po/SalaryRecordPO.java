package com.example.task.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 薪酬记录持久层对象
 */
@Data
public class SalaryRecordPO {
    /**
     * 薪酬记录ID
     */
    @NotNull(message = "薪资记录的ID不能为空")
    private Long recordId;

    /**
     * 岗位工资
     */
    @NotNull(message = "薪资记录中的岗位工资不能为空")
    private BigDecimal postPay;

    /**
     * 绩效工资
     */
    @NotNull(message = "薪资记录中的绩效工资不能为空")
    private BigDecimal performancePay;

    /**
     * 工龄工资
     */
    @NotNull(message = "薪资记录中的工龄工资不能为空")
    private BigDecimal standingPay;

    /**
     * 津贴补助
     */
    @NotNull(message = "薪资记录中的津贴补助不能为空")
    private BigDecimal allowance;

    /**
     * 个人所得税
     */
    @NotNull(message = "薪资记录中的个人所得税不能为空")
    private BigDecimal personalIncomeTax;

    /**
     * 员工ID
     */
    @NotNull(message = "薪资记录中的员工ID不能为空")
    private Long eeId;

    /**
     * 薪酬发放日
     */
    @NotNull(message = "薪资记录中的薪资发放日不能为空")
    private String wageDay;

    /**
     * 总收入
     */
    @NotNull(message = "总收入不能为空")
    private BigDecimal income;
}
