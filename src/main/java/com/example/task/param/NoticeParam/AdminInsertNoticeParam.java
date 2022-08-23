package com.example.task.param.NoticeParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 管理员新增公告参数
 */
@Data
public class AdminInsertNoticeParam {
    /**
    * 公告标题
    */
    @NotBlank(message = "公告标题不能为空")
    @Length(max = 30,message = "公告标题最大长度为30")
    private String title;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空")
    @Length(message = "公告内容字数最大值为1000")
    private String content;

    /**
     * 接收部门ID列表
     */
    @Size(min = 1,message = "接收部门ID列表不能为空")
    private List<String> receiverDepIdList;

    /**
     * 公告状态
     */
    @NotNull(message = "公告状态不能为空")
    private Integer noticeStatus;
}
