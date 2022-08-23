package com.example.task.param.NoticeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 管理员修改公告参数
 */
@Data
public class AdminModifyNoticeParam {
    /**
     * 公告ID
     */
    @NotNull(message = "公告ID不能为空")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Length(max = 30,message = "公告标题最大长度为30")
    private String title;

    /**
     * 公告内容
     */
    @Length(message = "公告内容字数最大值为1000")
    private String content;
}
