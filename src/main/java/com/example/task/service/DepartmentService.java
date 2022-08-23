package com.example.task.service;

import com.example.task.vo.DepartmentVO;
import com.example.task.vo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 123
* @description 针对表【t_department(部门表)】的数据库操作Service
* @createDate 2022-07-25 16:39:56
*/
@Service
public interface DepartmentService {

    void insert(Long departmentId, String departmentName, Long parentDepId);

    void delete(List<Long> depIdList);

    void update(Long departmentId,String departmentName, Long parentDepId);

    PageResult<DepartmentVO> search(Long departmentId, String departmentName, Long parentId, Integer current, Integer pageSize);
}
