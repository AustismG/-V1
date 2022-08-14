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

    Employee login(Long eeId, String password);

    void modifyOwnInfo(Long eeId, EmployeeModifyOwnInfoParam employeeModifyOwnInfoParam);

    void insert(Long eeId, String eeName, Integer sex, String phone, String departmentName, Integer role);

    void delete(List<Long> eeIdList);

    void AdminModifyEmployeeInfo(String eeName, Integer sex, String departmentName, String password, String phone, Integer role,Long eeId);

    PageResult<EmployeeVO> search(Long eeId, String eeName, String departmentName, String phone, Integer role, Integer current, Integer pageSize);

    void updatePassword(String originPassword, String modifiedPassword, Long employeeId);
}
