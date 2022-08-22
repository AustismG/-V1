package com.example.task.param.NoticeParam;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 */
@Data
public class EmployeeGetNoticeParam {
    @NotNull(message = "公告状态不能为空")
    @Max(value = 1, message = "公告状态值最大为1")
    @Min(value = 0,message = "公告状态值最小为0")
    private Long noticeStatus;
}
