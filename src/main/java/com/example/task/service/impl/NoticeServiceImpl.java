package com.example.task.service.impl;

import com.example.task.entity.Employee;
import com.example.task.mapper.NoticeMapper;
import com.example.task.service.NoticeService;
import com.example.task.utils.JWTUtil;
import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 123
* @description 针对表【t_notice(公告表)】的数据库操作Service实现
* @createDate 2022-08-11 14:28:09
*/
@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public void insert(String title, String content,String authorization) {
        JWTUtil jwtUtil = new JWTUtil();
        Employee employee = jwtUtil.getEmployeeFromToken(authorization);
        noticeMapper.adminInsert(title,content,employee.getEeId());
    }

    @Override
    @Transactional
    public void delete(List<Long> noticeIdList) {
        noticeMapper.delete(noticeIdList);
    }

    @Override
    public void update(Long noticeId,String title, String content) {
        noticeMapper.update(noticeId,title, content);
    }

    @Override
    public PageResult<NoticeVO> search(Long noticeId,
                                       Long publisherId,
                                       String title,
                                       String content,
                                       String startDate,
                                       String endDate,
                                       Integer current,
                                       Integer pageSize) {
        PageHelper.startPage(current, pageSize);
        List<NoticeVO> list = noticeMapper.getNoticeList(noticeId,publisherId,title,content,startDate,endDate);
        PageInfo<NoticeVO> pageInfo = new PageInfo<>(list);

        PageResult<NoticeVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setNext(pageInfo.isHasNextPage() ? pageInfo.getNextPage() : -1);
        pageResult.setPrev(pageInfo.isHasPreviousPage() ? pageInfo.getPrePage() : -1);
        pageResult.setList(list);
        return pageResult;
    }
}




