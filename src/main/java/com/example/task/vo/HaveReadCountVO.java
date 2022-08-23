package com.example.task.vo;

import lombok.Data;

/**
 * 公告已读相关信息
 */
@Data
public class HaveReadCountVO {
    /**
     * 已读人员姓名
     **/
    private String employeeName;

    /**
     * 已读人员部门
     **/
    private String employeeDepName;

    /**
     * 已读时间
     **/
    private String readTime;
}
