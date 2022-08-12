package com.example.task.param.SalaryRecordParam;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 管理员修改薪酬记录参数
 */
@Data
public class AdminModifyRecordParam {
    /**
     * 薪酬记录ID
     */
    @NotNull(message = "记录ID不能为空")
    private Long recordId;

    /**
     * 留言内工位工资
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
}
