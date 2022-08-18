package com.example.task.controller;

import com.example.task.param.NoticeParam.AdminInsertNoticeParam;
import com.example.task.param.NoticeParam.AdminModifyNoticeParam;
import com.example.task.param.NoticeParam.SearchNoticeParam;
import com.example.task.param.PagingParam;
import com.example.task.service.NoticeService;
import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/system/admins")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * @Author Gzy
     * @Description 管理员添加一条公告
     * @Param [adminInsertNoticeParam, authorization]
     * @return void
     * @is_Available 测试已通过!       
     **/
    @PostMapping("/notices")
    public void insert(@RequestBody @Valid AdminInsertNoticeParam adminInsertNoticeParam,@RequestHeader String authorization) {
        noticeService.insert(adminInsertNoticeParam.getTitle(), adminInsertNoticeParam.getContent(),authorization);
    }

    /**
     * @Author Gzy
     * @Description 管理员根据ID删除公告
     * @Param [adminDelNoticeParam]
     * @return void
     * @is_Available 测试已通过!       
     **/
    @DeleteMapping("/notices/{noticeId}")
    public void delete(@PathVariable Long noticeId) {
        noticeService.delete(noticeId);
    }

    /**
     * @Author Gzy
     * @Description 管理员更新公告
     * @Param [adminModifyNoticeParam]
     * @return void
     * @is_Available 测试已通过!       
     **/
    @PutMapping("/notices/{noticeId}")
    public void update(@RequestBody AdminModifyNoticeParam adminModifyNoticeParam,@PathVariable Long noticeId) {
        noticeService.update(noticeId,adminModifyNoticeParam.getTitle(), adminModifyNoticeParam.getContent());
    }

    /**
     * @Author Gzy
     * @Description 管理员按条件搜索公告
     * @Param [searchNoticeParam, pagingParam]
     * @return com.example.task.vo.PageResult<co    m.example.task.vo.NoticeVO>
     * @is_Available 测试已通过!
     **/
    @GetMapping("/notices")
    public PageResult<NoticeVO> search(@Valid SearchNoticeParam searchNoticeParam, PagingParam pagingParam) {
        return noticeService.search(searchNoticeParam.getNoticeId(),
                searchNoticeParam.getPublisherId(),
                searchNoticeParam.getTitle(),
                searchNoticeParam.getContent(),
                searchNoticeParam.getStartDate(),
                searchNoticeParam.getEndDate(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }
}
