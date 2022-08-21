package com.example.task.param.NoticeParam;

import lombok.Data;

import java.util.List;

/**
 * @Description: 管理员发布草稿中的公告
 */
@Data
public class AdminPublishNoticeParam {
    /**
     * 接收者的部门ID列表
     **/
    List<String> receiverDepIdList;
}
