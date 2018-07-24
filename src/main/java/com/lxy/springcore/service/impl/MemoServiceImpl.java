package com.lxy.springcore.service.impl;

import com.lxy.springcore.dao.JdbcTemplateDao;
import com.lxy.springcore.entity.MemoGroup;
import com.lxy.springcore.service.MemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 16:24
 */
//业务层实现
@Service
@Transactional
public class MemoServiceImpl implements MemoService {
    @Autowired
    private JdbcTemplateDao memoGroupDao;

    private final Logger logger = LoggerFactory.getLogger(MemoServiceImpl.class);

    public void insertMemoSevice(MemoGroup memoGroup) {
        if(memoGroup == null)
        {
            throw new IllegalArgumentException("MemoGroup arguments error");
        }
        if(StringUtils.isEmpty(memoGroup.getName()) || StringUtils.isEmpty(memoGroup.getCreateTime()))
        {
            throw new IllegalArgumentException("MemoGroup arguments wrong");
        }
        int count = memoGroupDao.selectMemoByName(memoGroup.getName());
        if(count > 0)
        {
            logger.debug("The MemoGroup has exit");
            throw new RuntimeException("The MemoGroup has exit");
        }
        else
        {
            logger.debug("insert MemoGroup res is"+ memoGroup);
            memoGroupDao.insertMemo(memoGroup);
        }
    }

    public void updataMemoService(int id,String name) {
        int count = memoGroupDao.selectMemoByName(name);
        if(count == 0)
        {
            logger.debug("The MemoGroup is not exit");
            throw new RuntimeException("The MemoGroup is not exit");
        }
        else
        {
            logger.debug("Update MemoGroup res is"+ id + name);
            memoGroupDao.updateMemo(id,name);
        }
    }

    public void deleteMemoService(int id) {
        if(id == 0)
        {
            logger.debug("The MemoGroup is not exit");
            throw new RuntimeException("The MemoGroup is not exit");
        }
        else
        {
            logger.debug("delete MemoGroup res is"+ id);
            memoGroupDao.deleteMemo(id);
        }
    }

    public MemoGroup selectMemoServiceById(int id) {
        if(id <= 0)
        {
            logger.debug("The id is illegal");
            return null;
        }
        List<MemoGroup> memoGroups = memoGroupDao.selectMemoById(id);
        if(memoGroups.isEmpty())
        {
            logger.debug("The MemoGroup is not found");
            throw new IllegalArgumentException("Id = " + id + "not found a MemoGroup");
        }
        if(memoGroups.size() > 1)
        {
            logger.debug("The MemoGroup is more than one");
            throw new RuntimeException("Id=" + id + " found more than one MemoGroup");
        }
        logger.debug("Select MemoGroup res is"+ id);
        return memoGroups.get(0);
    }

    public void selectMemoServiceByTime(Date start, Date end) {
        List<MemoGroup> list = memoGroupDao.selectMemoByTime(start,end);
        if(start == null || end == null)
        {
            throw new IllegalArgumentException("Is empty");
        }
        else if(list == null)
        {
            logger.debug("The MemoGroup is Empty");
        }
        else
        {
            logger.info("The result is" + list);
        }
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        MemoServiceImpl memoService = (MemoServiceImpl) applicationContext.getBean(MemoService.class);
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setId(13);
        memoGroup.setName("LYR");
        memoGroup.setCreateTime(new Date());
        memoGroup.setModifyTime(new Date());
        memoService.insertMemoSevice(memoGroup);
    }
}
