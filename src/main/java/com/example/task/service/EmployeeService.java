package com.example.task.service;

import com.example.task.entity.Employee;
import com.example.task.param.RegisterParam;
import com.example.task.param.EmployeeParam.EmployeeModifyOwnInfoParam;
import com.example.task.vo.EmployeeVO;
import com.example.task.vo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 123
* @description 针对表【t_employee(员工表)】的数据库操作Service
* @createDate 2022-07-25 17:02:12
*/
@Service
public interface EmployeeService  {
    void register(RegisterParam registerParam);

    Employee login(Long employeeId, String password);

    void modifyOwnInfo(Long employeeId, EmployeeModifyOwnInfoParam employeeModifyOwnInfoParam);

    void insert(Long employeeId, String employeeName, Integer sex, String phone, Long departmentId, Integer role);

    void delete(Long employeeId);

    void adminModifyEmployeeInfo(String employeeName, Integer sex, Long departmentId, String password, String phone, Integer role,Long employeeId);

    PageResult<EmployeeVO> search(Long employeeId, String employeeName, Long departmentId, String phone, Integer role, Integer current, Integer pageSize);

    void updatePassword(String originalPassword, String modifiedPassword, Long employeeId);
}
