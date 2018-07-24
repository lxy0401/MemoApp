package com.lxy.springcore.service;

import com.lxy.springcore.entity.MemoGroup;

import java.util.Date;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 15:57
 */
//业务接口
public interface MemoService {
    void insertMemoSevice(MemoGroup memoGroup);

    void updataMemoService(int id, String name);

    void deleteMemoService(int id);

    MemoGroup selectMemoServiceById(int id);

    void selectMemoServiceByTime(Date start, Date end);

}
