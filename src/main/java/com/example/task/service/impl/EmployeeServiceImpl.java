package com.example.task.service.impl;

import com.example.task.constant.ResultCodeEnum;
import com.example.task.entity.Employee;
import com.example.task.exception.BusinessException;
import com.example.task.mapper.DepartmentMapper;
import com.example.task.mapper.EmployeeMapper;
import com.example.task.param.RegisterParam;
import com.example.task.service.EmployeeService;
import com.example.task.param.EmployeeParam.EmployeeModifyOwnInfoParam;
import com.example.task.vo.EmployeeVO;
import com.example.task.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Gzy
 * @description 针对表【t_employee(员工表)】的数据库操作Service实现
 * @createDate 2022-07-25 17:02:12
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public void register(RegisterParam registerParam) {
        Long employeeId = registerParam.getEmployeeId();
        String employeeName = registerParam.getEmployeeName();
        String password = registerParam.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        Long departmentId = registerParam.getDepartmentId();
        Integer sex = registerParam.getSex();
        String phone = registerParam.getPhone();

        //首先用前端传过来的员工ID在数据库里查询，看看是否已经存在这个用户，如果存在说明这个用户已经注册过了
        Employee employee = employeeMapper.findEmployeeById(employeeId);
        if (employee != null) {
            throw new BusinessException(ResultCodeEnum.USERNAME_ALREADY_EXIST, "用户已存在");
        }

        //员工注册时，系统通过前端提供的部门ID查询对应的部门名称，帮员工写入对应字段
        //getIdByName()实现了“通过部门ID查询部门名称”的功能
        String departmentName = departmentMapper.getDepNameByDepId(departmentId);
        try {
            employeeMapper.insert(employeeId, employeeName, encodedPassword, sex,phone, departmentName, departmentId);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResultCodeEnum.PHONE_ALREADY_EXIST);
        }
    }

    @Override
    public Employee login(Long eeId, String password) {
        Authentication authentication;
        try {
            //authenticationManager 帮我们认证：从数据库中拿出加密后的密码然后用它与前端传过来的登录密码做对比
            //如果匹配，则返回对应的这个用户
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(eeId, password));
        } catch (AuthenticationException e) {
            log.warn("[登录失败]  尝试登录失败，失败原因：{}", e.getMessage());
            throw new BusinessException(ResultCodeEnum.WRONG_USERNAME_OR_PASSWORD);
        }
        return (Employee) authentication.getPrincipal();
    }

    @Override
    public void modifyOwnInfo(Long employeeId, EmployeeModifyOwnInfoParam employeeModifyOwnInfoParam) {
        employeeMapper.employeeModifyOwnInfo(employeeModifyOwnInfoParam.getEmployeeName(),
                employeeModifyOwnInfoParam.getSex(),
                employeeModifyOwnInfoParam.getPhone(),
                employeeModifyOwnInfoParam.getDepartmentId(),
                employeeId);
    }

    @Override
    public void insert(Long eeId,
                       String eeName,
                       Integer sex,
                       String phone,
                       Long departmentId,
                       Integer role) {
        String defaultPasswd = "123456@xyz";
        String encodedPasswd = passwordEncoder.encode(defaultPasswd);
        String departmentName = departmentMapper.getDepNameByDepId(departmentId);
        if (departmentName == null) {
            throw new BusinessException(ResultCodeEnum.WRONG_DEP,"部门不存在，请检查输入或联系管理员添加部门");
        }
        try {
            employeeMapper.adminInsert(eeId, eeName, sex, phone, departmentName, role, encodedPasswd, departmentId);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResultCodeEnum.PHONE_ALREADY_EXIST);
        }
    }

    @Override
    @Transactional
    public void delete(Long employeeId) {
        employeeMapper.delete(employeeId);
    }

    @Override
    public void AdminModifyEmployeeInfo(String employeeName,
                       Integer sex,
                       Long departmentId,
                       String password,
                       String phone,
                       Integer role,
                       Long employeeId) {
        String departmentName = null;
        if (password != null) {
            password = passwordEncoder.encode(password);
        }
        if (departmentId != null) {
            departmentName = employeeMapper.getDepNameByDepId(departmentId);
        }
        employeeMapper.adminModifyEmployeeInfo(employeeName, sex, departmentId,departmentName, password, phone, role,employeeId);
    }

    @Override
    public PageResult<EmployeeVO> search(Long employeeId,
                                         String employeeName,
                                         Long departmentId,
                                         String phone,
                                         Integer role,
                                         Integer current,
                                         Integer pageSize) {

        PageHelper.startPage(current, pageSize);
        List<EmployeeVO> list = employeeMapper.getEmployeeList(employeeId, employeeName, departmentId, phone, role);
        PageInfo<EmployeeVO> pageInfo = new PageInfo<>(list);

        PageResult<EmployeeVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setNext(pageInfo.isHasNextPage() ? pageInfo.getNextPage() : -1);
        pageResult.setPrev(pageInfo.isHasPreviousPage() ? pageInfo.getPrePage() : -1);
        pageResult.setList(list);
        return pageResult;
    }

    @Override
    public void updatePassword(String originalPassword, String modifiedPassword, Long employeeId) {
        //首先获取数据库中加密的原密码，并于前端传过来的密码进行匹配
        String encodedPassword = employeeMapper.getPasswordByEmployeeId(employeeId);
        if (!passwordEncoder.matches(originalPassword,encodedPassword)) {
            throw new BusinessException(ResultCodeEnum.WRONG_ORIGIN_PASSWORD);
        }
        //如果匹配成功，就可以修改密码了
        employeeMapper.updatePassword(employeeId, passwordEncoder.encode(modifiedPassword));
    }
}




