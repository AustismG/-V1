package com.example.task.controller;

import com.example.task.param.*;
import com.example.task.param.DepParam.AdminDelDepParam;
import com.example.task.param.DepParam.AdminModifyDepartmentParam;
import com.example.task.param.DepParam.SearchDepartmentParam;
import com.example.task.param.EmployeeParam.AdmInsertEmployeeParam;
import com.example.task.param.EmployeeParam.AdminDelEmployeeParam;
import com.example.task.param.EmployeeParam.AdminModifyEmployeeParam;
import com.example.task.param.EmployeeParam.SearchEmployeeParam;
import com.example.task.service.DepartmentService;
import com.example.task.service.EmployeeService;
import com.example.task.param.DepParam.AdminAddDepParam;
import com.example.task.vo.DepartmentVO;
import com.example.task.vo.EmployeeVO;
import com.example.task.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/system/admins")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    //-----------------------------管理员工接口-----------------------------

    /**
     * @Author Gzy
     * @Description 管理员添加一个新员工
     * @Param [admInsertEmployeeParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PostMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public void insert(@RequestBody @Valid AdmInsertEmployeeParam admInsertEmployeeParam) {
        employeeService.insert(admInsertEmployeeParam.getEeId(),
                admInsertEmployeeParam.getEeName(),
                admInsertEmployeeParam.getSex(),
                admInsertEmployeeParam.getPhone(),
                admInsertEmployeeParam.getDepartmentName(),
                admInsertEmployeeParam.getRole());
        log.info("insert方法执行完毕");
    }

    /**
     * @Author Gzy
     * @Description 管理员根据员工ID删除员工（可批量）
     * @Param [eeIdList]
     * @return void
     * @is_Available 测试已通过!
     **/
    @DeleteMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestBody AdminDelEmployeeParam adminDelEmployeeParam) {
        employeeService.delete(adminDelEmployeeParam.getEeIdList());
    }

    /**
     * @Author Gzy
     * @Description 管理员更新员工信息
     * @Param [adminModifyEmployeeParam, eeId]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PutMapping("/employees/{eeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody @Valid AdminModifyEmployeeParam adminModifyEmployeeParam, @PathVariable Long eeId) {
        employeeService.AdminModifyEmployeeInfo(adminModifyEmployeeParam.getEeName(),
                adminModifyEmployeeParam.getSex(),
                adminModifyEmployeeParam.getDepartmentName(),
                adminModifyEmployeeParam.getPassword(),
                adminModifyEmployeeParam.getPhone(),
                adminModifyEmployeeParam.getRole(),
                eeId);
    }

    /**
     * @Author Gzy
     * @Description 管理员根据条件查询员工信息
     * @Param [searchEmployeeParam, pagingParam]
     * @return com.example.task.vo.PageResult<com.example.task.vo.EmployeeVo>
     * @is_Available 测试已通过!
     **/
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public PageResult<EmployeeVO> search(@Valid SearchEmployeeParam searchEmployeeParam, PagingParam pagingParam) {
        return employeeService.search(searchEmployeeParam.getEeId(),
                searchEmployeeParam.getEeName(),
                searchEmployeeParam.getDepartmentName(),
                searchEmployeeParam.getPhone(),
                searchEmployeeParam.getRole(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }



    //-----------------------------管理部门接口-----------------------------

    /**
     * @Author Gzy
     * @Description 管理员添加一个新的部门
     * @Param [addDepParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PostMapping("/departments")
    @PreAuthorize("hasRole('ADMIN')")
    public void insertDep(@RequestBody @Valid AdminAddDepParam adminAddDepParam) {
        departmentService.insert(adminAddDepParam.getDepartmentId(),
                adminAddDepParam.getDepartmentName(),
                adminAddDepParam.getParentDepName());
    }

    /**
     * @Author Gzy
     * @Description 管理员根据部门ID删除部门（可批量）
     * @Param [delDepParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @DeleteMapping("/departments")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDep(@RequestBody @Valid AdminDelDepParam adminDelDepParam) {
        departmentService.delete(adminDelDepParam.getDepIdList());
    }

    /**
     * @Author Gzy
     * @Description 管理员修改部门信息
     * @Param [modifyDepartmentParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PutMapping("/departments")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateDep(@RequestBody @Valid AdminModifyDepartmentParam adminModifyDepartmentParam) {
        departmentService.update(adminModifyDepartmentParam.getDepartmentId(), adminModifyDepartmentParam.getDepartmentName(), adminModifyDepartmentParam.getParentDepName());
    }

    /**
     * @Author Gzy
     * @Description 管理员按条件查询部门信息
     * @Param [searchDepartmentParam, pagingParam]
     * @return com.example.task.vo.PageResult<com.example.task.vo.DepartmentVo>
     * @is_Available 测试已通过!
     **/
    @GetMapping("/departments")
    @PreAuthorize("hasRole('ADMIN')")
    public PageResult<DepartmentVO> searchDep(@Valid SearchDepartmentParam searchDepartmentParam, PagingParam pagingParam) {
        return departmentService.search(searchDepartmentParam.getDepartmentId(),
                searchDepartmentParam.getDepartmentName(),
                searchDepartmentParam.getParentId(),
                pagingParam.getCurrent(),
                pagingParam.getPageSize());
    }
}