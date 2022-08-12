package com.example.task.constant;

import java.math.BigDecimal;

/**
 * @Description:
 */
public enum TaxEnum {
    THREE_iNSURANCES_AND_ONE_FOUND(new BigDecimal(6900), "个人缴纳部分三险一金"),
    SPECIAL_DEDUCTION(new BigDecimal(4900),"专项扣除");

    private final BigDecimal value;
    private final String comment;

    TaxEnum(BigDecimal value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }
}
