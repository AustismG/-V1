package com.example.task.param.SalaryRecordParam;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员删除薪酬记录参数
 */
@Data
public class AdminDelRecordParam {
    /**
     * 薪酬记录ID列表
     */
    @NotNull(message = "薪酬记录ID列表不能为空")
    private List<Long> recordIdList;
}
