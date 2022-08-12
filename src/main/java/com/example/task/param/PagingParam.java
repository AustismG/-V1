package com.example.task.param;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 分页参数
 */
@Data
public class PagingParam {
    /**
     * 当前页码
     */
    @Min(value = 1, message = "页码最小为1")
    private Integer current = 1;
    /**
     * 每页显示几条
     */
    @Min(value = 1, message = "单页条数最小为1")
    @Max(value = 100, message = "单页条数最大为100")
    private Integer pageSize = 5;

}