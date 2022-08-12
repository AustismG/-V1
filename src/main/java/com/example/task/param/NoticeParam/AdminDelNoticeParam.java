package com.example.task.param.NoticeParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 */
@Data
public class AdminDelNoticeParam {
    /**
     * 公告ID列表
     */
    @NotNull(message = "公告ID列表不能为空")
    private List<Long> noticeIdList;
}
