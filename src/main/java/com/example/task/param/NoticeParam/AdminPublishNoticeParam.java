package com.example.task.param.NoticeParam;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 管理员发布草稿公告参数
 */
@Data
public class AdminPublishNoticeParam {
    /**
     * 接收者的部门ID列表
     **/
    @Size(min = 1,message = "接收者部门ID不能为空")
    List<String> receiverDepIdList;
}
