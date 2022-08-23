package com.example.task.param.NoticeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 管理员条件查询公告参数
 */
@Data
public class SearchNoticeParam {
    /**
     * 公告ID
     */
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
