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
    int insert(String title, String content, List<String> receiverDepIdList,Integer noticeStatus, Long publisherId);

    void delete(Long noticeId);

    void update(Long noticeId,String title, String content,Integer noticeStatus);

    PageResult<NoticeVO> search(Long noticeId, Long publisherId, String title, String content, String startDate, String endDate, Integer current, Integer pageSize);

    int publishNotice(Long publisherId, List<String> receiverDepIdList, Long noticeId);
}
