package com.lxy.springcore.dao;

import com.lxy.springcore.entity.MemoGroup;
import com.lxy.springcore.service.MemoService;

import java.util.Date;
import java.util.List;

/**
 * 便签组的数据库访问接口
 *
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 15:35
 */
//2..数据库接口
public interface JdbcTemplateDao {

    //插入新的便签组
    int insertMemo(MemoGroup memoGroup);

    //更新便签组
    int updateMemo(int id,String name);

    //根据Id查询便签组
    List<MemoGroup> selectMemoById(int id);

    //根据名称查询便签组
    int selectMemoByName(String name);

    //根据时间查询便签组
    List<MemoGroup> selectMemoByTime(Date start,Date end);

    //删除便签组
    int deleteMemo(int id);
}
