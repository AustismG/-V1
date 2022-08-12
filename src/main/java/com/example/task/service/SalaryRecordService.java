package com.example.task.service;

import com.example.task.vo.PageResult;
import com.example.task.vo.SalaryRecordVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 123
* @description 针对表【t_salary(薪酬记录表)】的数据库操作Service
* @createDate 2022-07-25 17:02:18
*/
@Service
public interface SalaryRecordService {

    void insert(Long eeId,
                BigDecimal postPay,
                BigDecimal performancePay,
                BigDecimal allowance,
                BigDecimal standingPay,
                String wageDay);

    void delete(List<Long> recordIdList);

    void update(Long recordId,
                BigDecimal postPay,
                BigDecimal performancePay,
                BigDecimal standingPay,
                BigDecimal allowance);

    PageResult<SalaryRecordVO> search(Long recordId,
                                      BigDecimal postPay,
                                      BigDecimal performancePay,
                                      BigDecimal standingPay,
                                      BigDecimal allowance,
                                      String startDate,
                                      String endDate,
                                      Integer current,
                                      Integer pageSzie);
}
