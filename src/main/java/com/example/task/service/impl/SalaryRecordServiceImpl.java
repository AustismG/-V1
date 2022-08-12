package com.example.task.service.impl;

import com.example.task.constant.ResultCodeEnum;
import com.example.task.constant.TaxEnum;
import com.example.task.exception.BusinessException;
import com.example.task.mapper.SalaryRecordMapper;
import com.example.task.po.SalaryRecordPO;
import com.example.task.service.SalaryRecordService;
import com.example.task.vo.PageResult;
import com.example.task.vo.SalaryRecordVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
* @author Gzy
* @description 针对表【t_salary_record(薪酬记录表)】的数据库操作Service实现
* @createDate 2022-07-25 17:02:18
*/
@Service
@Slf4j
public class SalaryRecordServiceImpl implements SalaryRecordService {
    @Autowired
    private SalaryRecordMapper salaryRecordMapper;

    @Override
    public void insert(Long eeId,
                       BigDecimal postPay,
                       BigDecimal performancePay,
                       BigDecimal allowance,
                       BigDecimal standingPay,
                       String wageDay) {
        //计算个人所得税
        BigDecimal income = postPay.add(performancePay).add(allowance).add(standingPay);
        BigDecimal personalIncomeTax = getTax(income,wageDay);
        try {
            salaryRecordMapper.insert(eeId,postPay, performancePay, allowance, standingPay, personalIncomeTax,wageDay);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResultCodeEnum.SALARYRECORD_ALREADY_EXIST);
        }

    }

    @Override
    @Transactional
    public void delete(List<Long> recordIdList) {
        salaryRecordMapper.delete(recordIdList);
    }

    @Override
    public void update(Long recordId,
                       BigDecimal postPay,
                       BigDecimal performancePay,
                       BigDecimal standingPay,
                       BigDecimal allowance) {
        SalaryRecordPO salaryRecordPo =  salaryRecordMapper.getSalaryRecordPo(recordId);
        BigDecimal originalIncome = salaryRecordPo.getIncome();
        if (postPay != null) {
            originalIncome = originalIncome.add(postPay.subtract(salaryRecordPo.getPostPay()));
        }
        if (performancePay != null) {
            originalIncome = originalIncome.add(performancePay.subtract(salaryRecordPo.getPerformancePay()));
        }
        if (standingPay != null) {
            originalIncome = originalIncome.add(standingPay.subtract(salaryRecordPo.getStandingPay()));
        }
        if (allowance != null) {
            originalIncome = originalIncome.add(allowance.subtract(salaryRecordPo.getAllowance()));
        }

        String wageDay = salaryRecordMapper.getRecordDateById(recordId);
        BigDecimal modifiedTax = getTax(originalIncome, wageDay);
        salaryRecordMapper.update(recordId, postPay, performancePay, standingPay, allowance, modifiedTax);

    }

    @Override
    public PageResult<SalaryRecordVO> search(Long recordId,
                                             BigDecimal postPay,
                                             BigDecimal performancePay,
                                             BigDecimal standingPay,
                                             BigDecimal allowance,
                                             String startDate,
                                             String endDate,
                                             Integer current,
                                             Integer pageSize) {
        PageHelper.startPage(current, pageSize);
        List<SalaryRecordVO> list = salaryRecordMapper.getRecordList(recordId, postPay, performancePay, standingPay, allowance,startDate,endDate);
        PageInfo<SalaryRecordVO> pageInfo = new PageInfo<>(list);

        PageResult<SalaryRecordVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setNext(pageInfo.isHasNextPage() ? pageInfo.getNextPage() : -1);
        pageResult.setPrev(pageInfo.isHasPreviousPage() ? pageInfo.getNextPage() : -1);
        pageResult.setList(list);
        return pageResult;
    }

    /**
     * @Author Gzy
     * @Description 根据总收入和工资发放日期计算“个人所得税”
     * @Param [income, wageDay]
     * @return java.math.BigDecimal
     * @is_Available 测试已通过!
     **/
    @Transactional
    BigDecimal getTax(BigDecimal income, String wageDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar calendar = new GregorianCalendar();
        int month = -1;
        try {
            //获取日期的月份
            date = simpleDateFormat.parse(wageDay);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            month = calendar.get(Calendar.MONTH) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //从数据库中得到上个月的个人所得税，用于计算本月个人所得税
        BigDecimal lastMonthTax = salaryRecordMapper.getTaxByMonth(month - 1);
        if (lastMonthTax == null) {
            lastMonthTax = new BigDecimal("0");
        }

        //方(tou)便(lan)起见，税率统一按10%算。
        //所得税计算方法参考网络
        BigDecimal personalIncomeTax ;
        personalIncomeTax= (income.subtract(TaxEnum.THREE_iNSURANCES_AND_ONE_FOUND.getValue()).
                subtract(TaxEnum.SPECIAL_DEDUCTION.getValue()).
                subtract(new BigDecimal("5000")).
                add(lastMonthTax)).
                multiply(new BigDecimal("0.1")).
                subtract(lastMonthTax);
        return personalIncomeTax;
    }
}




