package com.example.task.param.NoticeParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    @NotBlank
    private String content;

    /**
     * 接收部门ID列表
     */
    @Size(min = 1,message = "接收部门ID列表不能为空")
    private List<String> receiverDepIdList;

    /**
     * 公告状态
     */
    @NotNull
    private Integer noticeStatus;
}
