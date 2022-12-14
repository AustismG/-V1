package com.example.task.controller;

import com.example.task.param.*;
import com.example.task.param.DepParam.AdminDelDepParam;
import com.example.task.param.DepParam.AdminModifyDepartmentParam;
import com.example.task.param.DepParam.SearchDepartmentParam;
import com.example.task.param.EmployeeParam.AdmInsertEmployeeParam;
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
        employeeService.insert(admInsertEmployeeParam.getEmployeeId(),
                admInsertEmployeeParam.getEmployeeName(),
                admInsertEmployeeParam.getSex(),
                admInsertEmployeeParam.getPhone(),
                admInsertEmployeeParam.getDepartmentId(),
                admInsertEmployeeParam.getRole());
        log.info("insert方法执行完毕");
    }

    /**
     * @Author Gzy
     * @Description 管理员根据员工ID删除员工
     * @Param [eeIdList]
     * @return void
     * @is_Available 测试已通过!
     **/
    @DeleteMapping("/employees/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
    }

    /**
     * @Author Gzy
     * @Description 管理员更新员工信息
     * @Param [adminModifyEmployeeParam, eeId]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PutMapping("/employees/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody @Valid AdminModifyEmployeeParam adminModifyEmployeeParam, @PathVariable Long employeeId) {
        employeeService.adminModifyEmployeeInfo(adminModifyEmployeeParam.getEmployeeName(),
                adminModifyEmployeeParam.getSex(),
                adminModifyEmployeeParam.getDepartmentId(),
                adminModifyEmployeeParam.getPassword(),
                adminModifyEmployeeParam.getPhone(),
                adminModifyEmployeeParam.getRole(),
                employeeId);
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
        return employeeService.search(searchEmployeeParam.getEmployeeId(),
                searchEmployeeParam.getEmployeeName(),
                searchEmployeeParam.getDepartmentId(),
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
                adminAddDepParam.getParentDepId());
    }

    /**
     * @Author Gzy
     * @Description 管理员根据部门ID删除部门
     * @Param [delDepParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @DeleteMapping("/departments/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDep(@PathVariable Long departmentId) {
        departmentService.delete(departmentId);
    }

    /**
     * @Author Gzy
     * @Description 管理员修改部门信息
     * @Param [modifyDepartmentParam]
     * @return void
     * @is_Available 测试已通过!
     **/
    @PutMapping("/departments/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateDep(@RequestBody @Valid AdminModifyDepartmentParam adminModifyDepartmentParam, @PathVariable Long departmentId) {
        departmentService.update(departmentId, adminModifyDepartmentParam.getDepartmentName(), adminModifyDepartmentParam.getParentDepId());
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