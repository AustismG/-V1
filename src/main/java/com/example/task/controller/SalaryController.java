package com.example.task.controller;

import com.example.task.param.*;
import com.example.task.param.SalaryRecordParam.AdminDelRecordParam;
import com.example.task.param.SalaryRecordParam.AdminInsertSalaryRecordParam;
import com.example.task.param.SalaryRecordParam.AdminModifyRecordParam;
import com.example.task.param.SalaryRecordParam.SearchSalaryRecordParam;
import com.example.task.service.SalaryRecordService;
import com.example.task.vo.PageResult;
import com.example.task.vo.SalaryRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 */
@RestController
@RequestMapping("/system/admins")
@Slf4j
public class SalaryController {
    @Autowired
    private SalaryRecordService salaryRecordService;

    /**
     * @Author Gzy
     * @Description 管理员添加一条薪酬记录
     * @Param [insertSalaryRecordParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PostMapping("/records")
    @PreAuthorize("hasRole('ADMIN')")
    public void insert(@RequestBody @Valid AdminInsertSalaryRecordParam adminInsertSalaryRecordParam) {
        salaryRecordService.insert(adminInsertSalaryRecordParam.getEeId(),
                adminInsertSalaryRecordParam.getPostPay(),
                adminInsertSalaryRecordParam.getPerformancePay(),
                adminInsertSalaryRecordParam.getAllowance(),
                adminInsertSalaryRecordParam.getStandingPay(),
                adminInsertSalaryRecordParam.getWageDay());
    }

    /**
     * @Author Gzy
     * @Description 管理员删除薪酬记录
     * @Param [delRecordParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @DeleteMapping("/records")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestBody @Valid AdminDelRecordParam adminDelRecordParam) {
        salaryRecordService.delete(adminDelRecordParam.getRecordIdList());
    }

    /**
     * @Author Gzy
     * @Description 管理员更新薪酬记录
     * @Param [modifyRecordParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PutMapping("/records")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody AdminModifyRecordParam adminModifyRecordParam) {
        //修改了薪酬的某一部分，个人所得税也要跟着变化
        salaryRecordService.update(adminModifyRecordParam.getRecordId(),
                adminModifyRecordParam.getPostPay(),
                adminModifyRecordParam.getPerformancePay(),
                adminModifyRecordParam.getStandingPay(),
                adminModifyRecordParam.getAllowance());
    }

    /**
     * @Author Gzy
     * @Description 系统用户按条件查询薪酬信息
     * @Param [searchSalaryRecordParam, pagingParam]
     * @return com.example.task.vo.PageResult<com.example.task.vo.SearchRecordVO>
     * @is_Available 测试已通过!
     **/
    @GetMapping("/records")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public PageResult<SalaryRecordVO> search(@Valid SearchSalaryRecordParam searchSalaryRecordParam, PagingParam pagingParam) {
        return salaryRecordService.search(searchSalaryRecordParam.getRecordId(),
                searchSalaryRecordParam.getPostPay(),
                searchSalaryRecordParam.getPerformancePay(),
                searchSalaryRecordParam.getStandingPay(),
                searchSalaryRecordParam.getAllowance(),
                searchSalaryRecordParam.getStartDate(),
                searchSalaryRecordParam.getEndDate(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }
}
