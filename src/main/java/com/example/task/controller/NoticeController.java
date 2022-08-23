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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public Integer publish(@RequestBody AdminPublishNoticeParam adminPublishNoticeParam,
                        @PathVariable Long noticeId,
                        @AuthenticationPrincipal Employee employee) {

        return noticeService.publishNotice(employee.getEmployeeId(),
                adminPublishNoticeParam.getReceiverDepIdList(),
                noticeId);
    }

    /**
     * @Author Gzy
     * @Description 管理员根据ID物理删除公告
     * @Param [adminDelNoticeParam]
     * @return void
     * @is_Available 测试已通过!       
     **/
    @DeleteMapping("/notices/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public PageResult<NoticeVO> search(@Valid SearchNoticeParam searchNoticeParam,
                                       @AuthenticationPrincipal Employee employee,
                                       PagingParam pagingParam) {

        return noticeService.search(searchNoticeParam.getNoticeId(),
                employee.getEmployeeId(),
                searchNoticeParam.getTitle(),
                searchNoticeParam.getContent(),
                searchNoticeParam.getStartDate(),
                searchNoticeParam.getEndDate(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }

    /**
     * @Author Gzy
     * @Description 管理员查看公告已读人员的具体信息
     * @Param [noticeId, pagingParam]
     * @return com.example.task.vo.PageResult<com.example.task.vo.HaveReadCountVO>
     * @is_Available 测试已通过!
     **/
    @GetMapping("/notices/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public PageResult<HaveReadCountVO> getHaveReadCount(@PathVariable Long noticeId,
                                                        PagingParam pagingParam) {

        return noticeService.getHaveReadCount(noticeId,
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }
}
