package com.example.task.service;

import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 123
* @description 针对表【t_notice(公告表)】的数据库操作Service
* @createDate 2022-08-11 14:28:09
*/
@Service
public interface NoticeService {

    void insert(String title, String content,String authorization);

    void delete(Long noticeId);

    void update(Long noticeId,String title, String content);

    PageResult<NoticeVO> search(Long noticeId, Long publisherId, String title, String  content, String startDate, String endDate, Integer current, Integer pageSize);
}
