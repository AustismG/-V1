package com.example.task.param.NoticeParam;

import lombok.Data;

/**
 * @Description:
 */
@Data
public class SearchNoticeParam {
    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 发布公告者ID
     */
    private Long publisherId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String  content;

    /**
     * 查询区间起始日期
     */
    private String startDate;

    /**
     * 查询区间结束日期
     */
    private String endDate;
}
