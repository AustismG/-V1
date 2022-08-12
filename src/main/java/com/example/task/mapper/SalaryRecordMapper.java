package com.example.task.mapper;

import com.example.task.po.SalaryRecordPO;
import com.example.task.vo.SalaryRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 123
* @description 针对表【t_salary(薪酬记录表)】的数据库操作Mapper
* @createDate 2022-07-25 17:02:18
* @Entity com.example.task.entity.Salary
*/
@Mapper
public interface SalaryRecordMapper {

    void insert(@Param("eeId") Long eeId,
                @Param("postPay") BigDecimal postPay,
                @Param("performancePay") BigDecimal performancePay,
                @Param("allowance") BigDecimal allowance,
                @Param("standingPay") BigDecimal standingPay,
                @Param("personalIncomeTax") BigDecimal personalIncomeTax,
                @Param("wageDay") String wageDay);

    void delete(List<Long> recordIdList);

    String getRecordDateById(@Param("recordId") Long recordId);

    void update(@Param("recordId") Long recordId,
                @Param("postPay") BigDecimal postPay,
                @Param("performancePay") BigDecimal performancePay,
                @Param("standingPay") BigDecimal standingPay,
                @Param("allowance") BigDecimal allowance,
                @Param("modifiedTax") BigDecimal modifiedTax);

    List<SalaryRecordVO> getRecordList(@Param("recordId") Long recordId,
                                 @Param("postPay") BigDecimal postPay,
                                 @Param("performancePay") BigDecimal performancePay,
                                 @Param("standingPay") BigDecimal standingPay,
                                 @Param("allowance") BigDecimal allowance,
                                 @Param("startDate") String startDate,
                                 @Param("endDate") String endDate);

    BigDecimal getTaxByMonth(@Param("month") int month);

    SalaryRecordPO getSalaryRecordPo(@Param("recordId") Long recordId);
}




