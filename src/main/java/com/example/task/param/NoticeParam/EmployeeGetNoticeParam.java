package com.example.task.param.NoticeParam;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 员工获取 未读/已读 公告参数
 */
@Data
public class EmployeeGetNoticeParam {

    /**
     * 公告的状态（未读/已读）
     **/
    @NotNull(message = "公告状态不能为空")
    @Max(value = 1, message = "公告状态值最大为1")
    @Min(value = 0,message = "公告状态值最小为0")
    private Long noticeStatus;
}
