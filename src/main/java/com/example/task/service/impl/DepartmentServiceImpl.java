package com.example.task.service.impl;

import com.example.task.constant.ResultCodeEnum;
import com.example.task.exception.BusinessException;
import com.example.task.mapper.DepartmentMapper;
import com.example.task.mapper.EmployeeMapper;
import com.example.task.service.DepartmentService;
import com.example.task.vo.DepartmentVO;
import com.example.task.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 123
* @description 针对表【t_department(部门表)】的数据库操作Service实现
* @createDate 2022-07-25 16:39:56
*/
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void insert(Long departmentId, String departmentName, Long parentDepId) {
        String parentDepName = departmentMapper.getDepNameByDepId(parentDepId);
        if (parentDepName == null) {
            log.info("[错误提示] 输入的父部门ID是：" + parentDepId);
            throw new BusinessException(ResultCodeEnum.WRONG_DEP);
        }

        try {
            departmentMapper.insert(departmentId, departmentName, parentDepId);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResultCodeEnum.DEPNAME_ALREADY_EXIST);
        }

    }

    @Override
    public void delete(List<Long> depIdList) {
        for (Long depId : depIdList) {
            deleteDep(depId);
        }
    }

    @Override
    @Transactional
    public void update(Long departmentId,String modifiedDepartmentName, String modifiedParentDepName) {
        //1. 修改某个部门的名称
        if (modifiedDepartmentName != null) {
            departmentMapper.updateDepName(departmentId,modifiedDepartmentName);
            //用户的部门字段也得跟着修改
            String originDepartmentName = departmentMapper.getDepNameByDepId(departmentId);
            employeeMapper.updateDepName(originDepartmentName,modifiedDepartmentName);
        }

        //2. 修改某个部门的父部门(根据用户提供的部门名称，更新parent_id字段)
        if (modifiedParentDepName != null) {
            Long destParentId = departmentMapper.getDepIdByDepName(modifiedParentDepName);
            if (destParentId == null) {
                log.warn("[错误提示] 输入的父部门名称是：" + modifiedParentDepName);
                throw new BusinessException(ResultCodeEnum.WRONG_DEP);
            }
            departmentMapper.updateParentId(departmentId,destParentId);
        }
    }

    @Override
    public PageResult<DepartmentVO> search(Long departmentId,
                                           String departmentName,
                                           Long parentId,
                                           Integer current,
                                           Integer pageSize) {
        PageHelper.startPage(current, pageSize);
        List<DepartmentVO> list = departmentMapper.getDepList(departmentId, departmentName, parentId);
        PageInfo<DepartmentVO> pageInfo = new PageInfo<>(list);

        PageResult<DepartmentVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setNext(pageInfo.isHasNextPage() ? pageInfo.getNextPage() : -1);
        pageResult.setPrev(pageInfo.isHasPreviousPage() ? pageInfo.getPrePage() : -1);
        pageResult.setList(list);
        return pageResult;
    }


    /**
     * @Author Gzy
     * @Description 递归地删除部门及它的附属部门
     * @Param [depId]
     * @return void
     * @is_Available 测试已通过!
     **/
    @Transactional
    void deleteDep(Long depId){
        //判断：如果当前部门ID为null，则返回上一层
        if (depId == null) {
            return;
        }

        //根据当前部门ID查询它的子部门ID
        //递归调用，直到子部门ID为null
        List<Long> sonIdList = departmentMapper.getSonDepId(depId);
        for (Long sonIds : sonIdList) {
            deleteDep(sonIds);
        }

        //递归到叶子节点时，开始自底向上删除部门
        departmentMapper.delete(depId);
    }

}




