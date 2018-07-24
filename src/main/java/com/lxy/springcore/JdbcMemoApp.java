package com.lxy.springcore;

import com.lxy.springcore.dao.JdbcTemplateDao;
import com.lxy.springcore.dao.impl.JdbcTemplateDaoImpl;
import com.lxy.springcore.entity.MemoGroup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 15:57
 */
public class JdbcMemoApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        JdbcTemplateDao jdbcTemplate = applicationContext.getBean(JdbcTemplateDaoImpl.class);
//        MemoGroup memoGroup = new MemoGroup();
//        memoGroup.setId(4);
//        memoGroup.setName("DRET");
//        memoGroup.setCreateTime(new Date());
//        memoGroup.setModifyTime(new Date());
        //jdbcTemplate.insertMemo(memoGroup);
        //jdbcTemplate.updateMemo(4,"Apple");
        //jdbcTemplate.selectMemoById(5);
        //jdbcTemplate.deleteMemo(7);
        //jdbcTemplate.selectMemoByName("Spring");
        jdbcTemplate.selectMemoByTime(new Date(),new Date());

    }
}
