package com.example.task.mapper;

import com.example.task.vo.DepartmentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 123
* @description 针对表【t_department(部门表)】的数据库操作Mapper
* @createDate 2022-07-25 16:39:56
* @Entity com.example.task.entity.Department
*/
@Mapper
public interface DepartmentMapper  {

    Long getDepIdByDepName(@Param("departmentName") String departmentName);

    void insert(@Param("departmentId") Long departmentId,
                @Param("departmentName") String departmentName,
                @Param("parentDepId") Long parentDepId);

    void updateDepName(@Param("departmentId") Long departmentId,
                    @Param("departmentName") String departmentName);

    List<Long> getSonDepId(@Param("depId") Long depId);

    void delete(@Param("depId") Long depId);

    String getDepNameByDepId(@Param("departmentId") Long departmentId);

    void updateParentId(@Param("departmentId") Long departmentId,@Param("parentId") Long parentId);

    List<DepartmentVO> getDepList(@Param("departmentId") Long departmentId,
                               @Param("departmentName") String departmentName,
                               @Param("parentId") Long parentId);
}




