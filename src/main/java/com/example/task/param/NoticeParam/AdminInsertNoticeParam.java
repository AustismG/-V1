package com.example.task.param.NoticeParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 */
@Data
public class AdminInsertNoticeParam {
    /**
    * 公告标题
    */
    @NotBlank
    private String title;

    /**
     * 公告内容
     */
    @NotNull
    private String content;
}
