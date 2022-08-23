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
public interface EmployeeMapper {

    Employee findEmployeeById(@Param("employeeId") Long employeeId);

    int insert(@Param("employeeId") Long employeeId,
               @Param("employeeName") String employeeName,
               @Param("encodedPassword") String encodedPassword,
               @Param("sex") Integer sex,
               @Param("phone") String phone,
               @Param("departmentId") Long departmentId);

    void employeeModifyOwnInfo(
            @Param("employeeName") String employeeName,
            @Param("sex") Integer sex,
            @Param("phone") String phone,
            @Param("employeeId") Long employeeId);

    Employee getEmployeeByEId(@Param("employeeId") Long employeeId);

    List<EmployeeVO> getEmployeeList(@Param("employeeId") Long employeeId,
                                     @Param("employeeName") String employeeName,
                                     @Param("departmentId") Long departmentId,
                                     @Param("phone") String phone,
                                     @Param("role") Integer role);

    void adminInsert(@Param("employeeId") Long employeeId,
                     @Param("employeeName") String employeeName,
                     @Param("sex") Integer sex,
                     @Param("phone") String phone,
                     @Param("role") Integer role,
                     @Param("passwd") String passwd,
                     @Param("departmentId") Long departmentId);

    void adminModifyEmployeeInfo(@Param("employeeName") String employeeName,
                                 @Param("sex") Integer sex,
                                 @Param("departmentId") Long departmentId,
                                 @Param("password") String password,
                                 @Param("phone") String phone,
                                 @Param("role") Integer role,
                                 @Param("employeeId") Long employeeId);

    void delete(@Param("employeeId") Long employeeId);


    String getPasswordByEmployeeId(@Param("employeeId") Long employeeId);

    void updatePassword(@Param("employeeId") Long employeeId,
                        @Param("modifiedPassword") String modifiedPassword);

    String getDepNameByDepId(@Param("departmentId") Long departmentId);

    List<Long> getEmployeeIdByDepId(@Param("receiverDepId") String receiverDepId);
}




