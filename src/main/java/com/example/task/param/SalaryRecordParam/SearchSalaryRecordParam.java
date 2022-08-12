package com.example.task.param.SalaryRecordParam;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 条件搜索薪酬记录参数
 */
@Data
public class SearchSalaryRecordParam {
    /**
     * 员工ID
     */
    private Long eeId;

    /**
     * 薪酬记录ID
     */
    private Long recordId;

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
     * 查询区间起始日期
     */
    private String startDate;

    /**
     * 查询区间结束日期
     */
    private String endDate;
}
