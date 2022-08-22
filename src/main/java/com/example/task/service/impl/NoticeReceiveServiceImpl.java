package com.example.task.service.impl;

import com.example.task.mapper.NoticeReceiveMapper;
import com.example.task.service.NoticeReceiveService;
import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @author 123
* @description 针对表【t_notice_receive(公告接收表)】的数据库操作Service实现
* @createDate 2022-08-20 16:52:48
*/
@Service
public class NoticeReceiveServiceImpl implements NoticeReceiveService{
    @Autowired
    private NoticeReceiveMapper noticeReceiveMapper;

    @Override
    public void updateNoticeStatus(Long noticeId,
                                   Integer noticeStatus,
                                   Long receiverId) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String readTime = sdf.format(new Date());
        noticeReceiveMapper.updateNoticeStatus(noticeId,noticeStatus,receiverId,readTime);
    }

    @Override
    public void logicalDelete(Long noticeId,Long receiverId) {
        noticeReceiveMapper.logicalDelete(noticeId,receiverId);
    }

    @Override
    public PageResult<NoticeVO> getNotice(Long receiverId,
                                          Long noticeStatus,
                                          Integer current,
                                          Integer pageSize) {

        PageHelper.startPage(current, pageSize);
        List<NoticeVO> list = noticeReceiveMapper.getNoticeReceiveList(receiverId, noticeStatus);
        PageInfo<NoticeVO> pageInfo = new PageInfo(list);

        PageResult<NoticeVO> result = new PageResult<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        result.setPrev(pageInfo.isHasPreviousPage() ? pageInfo.getPrePage() : -1);
        result.setNext(pageInfo.isHasNextPage() ? pageInfo.getNextPage() : -1);
        return result;
    }
}




