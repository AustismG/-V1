package com.example.task.param.EmployeeParam;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员删除员工参数
 */
@Data
public class AdminDelEmployeeParam {
    /**
     * 员工ID
     */
    @NotNull(message = "员工ID列表不能为空")
    private List<Long> eeIdList;
}
