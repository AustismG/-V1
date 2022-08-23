package com.example.task.controller;

import com.example.task.entity.Employee;
import com.example.task.param.NoticeParam.*;
import com.example.task.param.PagingParam;
import com.example.task.service.NoticeService;
import com.example.task.vo.CommonResult;
import com.example.task.vo.HaveReadCountVO;
import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Integer insert(@RequestBody @Valid AdminInsertNoticeParam adminInsertNoticeParam,
                          @AuthenticationPrincipal Employee employee) {

         return noticeService.insert(adminInsertNoticeParam.getTitle(),
                adminInsertNoticeParam.getContent(),
                adminInsertNoticeParam.getReceiverDepIdList(),
                adminInsertNoticeParam.getNoticeStatus(),
                employee.getEmployeeId());
    }

    /**
     * @return void
     * @Author Gzy
     * @Description 管理员发布草稿中的公告
     * @Param []
     * @is_Available 测试已通过!
     **/
    @PostMapping("/notices/{noticeId}")
    public Integer publish(@RequestBody AdminPublishNoticeParam adminPublishNoticeParam,
                        @PathVariable Long noticeId,
                        @AuthenticationPrincipal Employee employee) {

        return noticeService.publishNotice(employee.getEmployeeId(),
                adminPublishNoticeParam.getReceiverDepIdList(),
                noticeId);
    }

    /**
     * @Author Gzy
     * @Description 管理员根据ID删除公告（物理删除）
     * @Param [adminDelNoticeParam]
     * @return void
     * @is_Available 测试已通过!       
     **/
    @DeleteMapping("/notices/{noticeId}")
    public void PhysicalDelete(@PathVariable Long noticeId) {
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
    public void update(@RequestBody AdminModifyNoticeParam adminModifyNoticeParam,
                       @PathVariable Long noticeId) {

        noticeService.update(noticeId,adminModifyNoticeParam.getTitle(),
                adminModifyNoticeParam.getContent());
    }

    /**
     * @Author Gzy
     * @Description 将已发布的某条公告，转发给其它部门
     * @Param [adminForwardNoticeParam, noticeId, employee]
     * @return java.lang.Integer
     * @is_Available 测试已通过!
     **/
    @PostMapping("/notices/otherDep/{noticeId}")
    public Integer forward(@RequestBody AdminForwardNoticeParam adminForwardNoticeParam,
                        @PathVariable Long noticeId,
                        @AuthenticationPrincipal Employee employee) {

       return noticeService.forward(employee.getEmployeeId(),
                adminForwardNoticeParam.getReceiverDepIdList(),
                noticeId);
    }


    /**
     * @Author Gzy
     * @Description 管理员按条件搜索公告
     * @Param [searchNoticeParam, pagingParam]
     * @return com.example.task.vo.PageResult<co    m.example.task.vo.NoticeVO>
     * @is_Available 测试已通过!
     **/
    @GetMapping("/notices")
    public PageResult<NoticeVO> search(@Valid SearchNoticeParam searchNoticeParam,
                                       PagingParam pagingParam) {

        return noticeService.search(searchNoticeParam.getNoticeId(),
                searchNoticeParam.getPublisherId(),
                searchNoticeParam.getTitle(),
                searchNoticeParam.getContent(),
                searchNoticeParam.getStartDate(),
                searchNoticeParam.getEndDate(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }

    @GetMapping("/notices/{noticeId}")
    public PageResult<HaveReadCountVO> getHaveReadCount(@PathVariable Long noticeId,
                                                        PagingParam pagingParam) {

        return noticeService.getHaveReadCount(noticeId,
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }
}
