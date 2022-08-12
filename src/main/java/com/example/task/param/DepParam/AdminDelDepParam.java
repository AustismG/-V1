package com.example.task.param.DepParam;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员删除部门参数
 */
@Data
public class AdminDelDepParam {
    /**
     * 部门ID
     */
    @NotNull(message = "部门ID列表不能为空")
    private List<Long> depIdList;
}
