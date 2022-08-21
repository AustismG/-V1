package com.example.task.service.impl;

import com.example.task.constant.ResultCodeEnum;
import com.example.task.exception.BusinessException;
import com.example.task.mapper.EmployeeMapper;
import com.example.task.mapper.NoticeMapper;
import com.example.task.mapper.NoticeReceiveMapper;
import com.example.task.mapper.NoticeSendMapper;
import com.example.task.service.NoticeService;
import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private NoticeSendMapper noticeSendMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private NoticeReceiveMapper noticeReceiveMapper;

    @Override
    @Transactional
    public int insert(String title,
                       String content,
                       List<String> receiverDepIdList,
                       Integer noticeStatus,
                       Long publisherId) {

        String receiverDepIdStr = StringUtils.join(receiverDepIdList);
        int employeeCount = 0;
        try {
            //1. 管理员选择暂存为草稿，不发布
            if (noticeStatus == 1) {
                noticeMapper.adminInsert(title, content, receiverDepIdStr, noticeStatus, publisherId);
            }
            //2. 管理员选择发布某条公告
            //先将该条公告的信息存入“公告表”，再向“公告发送表”、“公告接收表”同步数据，同步后即完成“发布”动作
            else if (noticeStatus == 0) {
                noticeMapper.adminInsert(title, content, receiverDepIdStr, noticeStatus, publisherId);
                Long noticeId = noticeMapper.getNoticeIdByTitle(publisherId, title);
                employeeCount = publishNotice(publisherId, receiverDepIdList, noticeId);
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ResultCodeEnum.TITLE_ALREADY_EXIST);
        }

        return employeeCount;
    }

    /**
     * @Author Gzy
     * @Description 发布公告，向“公告发送表”、“公告接收表”同步数据
     * @Param [employeeId, receiverIdList, noticeId]
     * @return void
     * @is_Available 测试未通过!
     **/
    public int publishNotice(Long publisherId,
                              List<String> receiverDepIdList,
                              Long noticeId) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        int employeeCount = 0;
        for (String receiverDepId : receiverDepIdList) {
            List<Long> receiverIdList = employeeMapper.getEmployeeIdByDepId(receiverDepId);
            employeeCount += receiverIdList.size();
            //同步“公告发送表”
            for (Long receiverId : receiverIdList) {
                noticeSendMapper.insert(publisherId,receiverId,noticeId,currentTime);
                noticeReceiveMapper.insert(noticeId,receiverId);
            }
        }
        return employeeCount;
    }


    @Override
    @Transactional
    public void delete(Long noticeId) {
        noticeMapper.delete(noticeId);
    }

    @Override
    public void update(Long noticeId,
                       String title,
                       String content,
                       Integer noticeStatus) {
        noticeMapper.update(noticeId,title, content,noticeStatus);
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




