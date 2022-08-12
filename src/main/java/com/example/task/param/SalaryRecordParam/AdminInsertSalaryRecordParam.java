package com.example.task.param.SalaryRecordParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 管理员添加薪酬记录参数
 */
@Data
public class AdminInsertSalaryRecordParam {
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
     * 工资发放日
     */
    @NotBlank(message = "工资发放日期不能为空")
    private String wageDay;
}
