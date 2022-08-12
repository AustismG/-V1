package com.example.task.mapper;

import com.example.task.entity.Employee;
import com.example.task.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 123
* @description 针对表【t_employee(员工表)】的数据库操作Mapper
* @createDate 2022-07-25 17:02:12
* @Entity com.example.task.entity.Employee
*/
@Mapper
public interface EmployeeMapper  {

    Employee findEmployeeById(@Param("eeId") Long eeId);

    int insert(@Param("eeId")Long eId,
               @Param("eeName") String eName,
               @Param("encodedPassword") String encodedPassword,
               @Param("sex") Integer sex,
               @Param("phone") String phone,
               @Param("departmentName") String departmentName,
               @Param("departmentId") Long departmentId);

    void employeeModifyOwnInfo(@Param("password") String password,
                    @Param("eeName") String eName,
                    @Param("sex") Integer sex,
                    @Param("phone") String phone,
                    @Param("departmentName") String departmentName);

    Employee getEmployeeByEId(Long eeId);

    List<EmployeeVO> getEmployeeList(@Param("eeId") Long eeId,
                             @Param("eeName") String eeName,
                             @Param("departmentName") String departmentName,
                             @Param("phone") String phone,
                             @Param("role") Integer role);

    void adminInsert(@Param("eeId") Long eeId,
                     @Param("eeName") String eeName,
                     @Param("sex") Integer sex,
                     @Param("phone") String phone,
                     @Param("departmentName") String departmentName,
                     @Param("role") Integer role,
                     @Param("passwd")String passwd,
                     @Param("departmentId")Long departmentId);

    void adminModifyEmployeeInfo(@Param("eeName")String eeName,
                @Param("sex") Integer sex,
                @Param("departmentName") String departmentName,
                @Param("password")String password,
                @Param("phone")String phone,
                @Param("role")Integer role,
                @Param("eeId") Long eeId);

    void delete(List<Long> eeIdList);

    void updateDepName(@Param("originDepartmentName") String originDepartmentName,
                       @Param("modifiedDepartmentName") String modifiedDepartmentName);
}




