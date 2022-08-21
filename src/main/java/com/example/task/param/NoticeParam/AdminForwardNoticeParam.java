package com.example.task.param.NoticeParam;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 */
@Data
public class AdminForwardNoticeParam {
    /**
     * 接收者的部门ID列表
     **/
    List<String> receiverDepIdList;
}
