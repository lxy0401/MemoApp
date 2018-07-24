package com.lxy.springcore;

import com.lxy.springcore.entity.MemoGroup;
import com.lxy.springcore.service.MemoService;
import com.lxy.springcore.service.impl.MemoServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 17:14
 */
public class MemoTest {

    private static ApplicationContext applicationContext ;
    private static MemoService memoService ;

    @BeforeClass
    public static void beforeClass()
    {
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        memoService = applicationContext.getBean(MemoServiceImpl.class);
    }

    @Test
    public void test_insertMemoSevice()
    {
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setId(12);
        memoGroup.setName("LXH");
        memoGroup.setCreateTime(new Date());
        memoGroup.setModifyTime(new Date());
        memoService.insertMemoSevice(memoGroup);
    }

    @Test
    public void test_updataMemoService()
    {
        memoService.updataMemoService(11,"app");
    }

    @Test
    public void test_deleteMemoService()
    {
        memoService.deleteMemoService(10);
    }

    @Test
    public void test_selectMemoService()
    {
        memoService.selectMemoServiceById(5);
    }

    @Test
    public void test_selectMemoServiceByTime()
    {
        memoService.selectMemoServiceByTime(new Date(),new Date());
    }
}
