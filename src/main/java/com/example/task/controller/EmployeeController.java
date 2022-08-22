package com.example.task.controller;

import com.example.task.entity.Employee;
import com.example.task.param.EmployeeParam.UpdatePasswordParam;
import com.example.task.param.LoginParam;
import com.example.task.param.NoticeParam.EmployeeGetNoticeParam;
import com.example.task.param.NoticeParam.SearchNoticeParam;
import com.example.task.param.PagingParam;
import com.example.task.param.RegisterParam;
import com.example.task.service.EmployeeService;
import com.example.task.service.NoticeReceiveService;
import com.example.task.utils.JWTUtil;
import com.example.task.param.EmployeeParam.EmployeeModifyOwnInfoParam;
import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/system/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private NoticeReceiveService noticeReceiveService;

    /**
     * @return void
     * @Author Gzy
     * @Description 系统用户注册
     * @Param [registerParam]
     * @is_Available 测试已通过!
     **/
    @PostMapping("/register")
    public void register(@RequestBody RegisterParam registerParam) {
        log.info("这是接收到的Body：" + registerParam);
        employeeService.register(registerParam);
    }

    /**
     * @return java.lang.Object
     * @Author Gzy
     * @Description 系统用户登录
     * @Param [loginParam, httpServletResponse]
     * @is_Available 测试已通过!
     **/
    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginParam loginParam, HttpServletResponse httpServletResponse) {
        Employee employee = employeeService.login(loginParam.getEmployeeId(), loginParam.getPassword());
        String token = JWTUtil.createToken(employee);
        httpServletResponse.setHeader("token", token);
        return new Object();
    }

    /**
     * @return void
     * @Author Gzy
     * @Description 员工修改自己的信息
     * @Param [employee, employeeEditVo]
     * @is_Available 测试已通过!
     **/
    @PutMapping("/info")
    @PreAuthorize("hasRole('USER')")
    public void modifyOwnInfo(@AuthenticationPrincipal Employee employee,
                              @RequestBody @Valid EmployeeModifyOwnInfoParam employeeModifyOwnInfoParam) {
        employeeService.modifyOwnInfo(employee.getEmployeeId(), employeeModifyOwnInfoParam);
    }

    /**
     * @return void
     * @Author Gzy
     * @Description 员工修改自己的密码
     * @Param [updatePasswordParam, employeeId]
     * @is_Available 测试已通过!
     **/
    @PutMapping("/password/{employeeId}")
    public void updatePassword(@RequestBody UpdatePasswordParam updatePasswordParam, @PathVariable Long employeeId) {
        employeeService.updatePassword(updatePasswordParam.getOriginalPassword(),
                updatePasswordParam.getModifiedPassword(),
                employeeId);
    }

    /**
     * @return
     * @Author Gzy
     * @Description 获取 未读/已读 的公告
     * @Param
     * @is_Available 测试未通过!
     **/
    @GetMapping("/notices")
    public PageResult<NoticeVO> getNotice(@RequestBody EmployeeGetNoticeParam employeeGetNoticeParam,
                                          @AuthenticationPrincipal Employee employee,
                                          PagingParam pagingParam) {

        return noticeReceiveService.getNotice(employee.getEmployeeId(),
                employeeGetNoticeParam.getNoticeStatus(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }

    /**
     * @return
     * @Author Gzy
     * @Description 员工在前端点击”已读“后，更新表中该条数据的”读取状态“
     * @Param
     * @is_Available 测试未通过!
     **/
    @PutMapping("/notices/{noticeId}/{noticeStatus}")
    public void updateNoticeStatus(@PathVariable Long noticeId,
                                   @PathVariable Integer noticeStatus,
                                   @AuthenticationPrincipal Employee employee) {

        noticeReceiveService.updateNoticeStatus(noticeId, noticeStatus, employee.getEmployeeId());
    }


    @PutMapping("/notices/{noticeId}")
    public void logicalDelete(@PathVariable Long noticeId,
                              @AuthenticationPrincipal Employee employee) {

        noticeReceiveService.logicalDelete(noticeId,employee.getEmployeeId());
    }
}

//普通员工token
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbXBsb3llZSI6eyJyb2xlIjoiMCIsImlkIjoiODAwMDEwIn0sImV4cCI6MTY1OTU5NTkyNX0.B4Sp3Jvq2N2Qx0y3buYM0RBm8UYSjj312Mxv2InmcKs