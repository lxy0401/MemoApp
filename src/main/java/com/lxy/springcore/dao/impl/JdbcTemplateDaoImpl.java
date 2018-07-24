package com.lxy.springcore.dao.impl;

import com.lxy.springcore.dao.JdbcTemplateDao;
import com.lxy.springcore.entity.MemoGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 15:08
 */
//3..数据库实现
@Repository
public class JdbcTemplateDaoImpl implements JdbcTemplateDao {

    //日志
    private final Logger logger = LoggerFactory.getLogger(JdbcTemplateDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //插入
    public int insertMemo(MemoGroup memoGroup) {
        int res = jdbcTemplate.update(
                "insert into memo_group(id, name, created_time, modify_time) values (?,?,?,? )",
                memoGroup.getId(), memoGroup.getName(), memoGroup.getCreateTime(), memoGroup.getModifyTime());
        logger.debug("insert MemoGroup result={}",memoGroup);
        return res;
    }

    //更新
    public int updateMemo(int id, String name) {
        int res = jdbcTemplate.update(
                "update memo_group set name = ? where id = ?",
                name,id);
        logger.debug("update MemoGroup id={} result={}",id,name);
        return res;
    }

    //根据时间查询查找
    public List<MemoGroup> selectMemoById(int id) {
        List<MemoGroup> memoGroups = jdbcTemplate.query(
                "select id,name,created_time,modify_time from memo_group where id = ?",
                new Object[]{id}, new RowMapper<MemoGroup>() {
            public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                MemoGroup memoGroup = new MemoGroup();
                memoGroup.setId(resultSet.getInt("id"));
                memoGroup.setName(resultSet.getString("name"));
                memoGroup.setCreateTime(resultSet.getDate("created_time"));
                memoGroup.setModifyTime(resultSet.getDate("modify_time"));
                return memoGroup;
            }
        });
        logger.debug("query MemoGroup id={} result={}", id, memoGroups);
        return memoGroups;
    }

    //根据名称查询
    public int selectMemoByName(String name) {
        int count = jdbcTemplate.queryForObject(
                "select count(id) from memo_group where name = ?",
                new Object[]{name},Integer.class);
        logger.debug("query MemoGroup ", name);
        return count;
    }

    public List<MemoGroup> selectMemoByTime(Date start, Date end) {
        List<MemoGroup> memoGroups = jdbcTemplate.query(
                "select id,name,created_time,modify_time from memo_group where created_time between ? and ?",
                 new RowMapper<MemoGroup>() {
                    public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                        MemoGroup memoGroup = new MemoGroup();
                        memoGroup.setId(resultSet.getInt("id"));
                        memoGroup.setName(resultSet.getString("name"));
                        memoGroup.setCreateTime(resultSet.getDate("created_time"));
                        memoGroup.setModifyTime(resultSet.getDate("modify_time"));
                        return memoGroup;
                    }
                },start,end);
        return null;
    }

//    //根据时间查询
//    public List<MemoGroup> selectMemoByTime(Date start, Date end) {
//        List<MemoGroup> memoGroups = jdbcTemplate.query(
//                "select id,name,created_time,modify_time from memo_group where created_time between ? and ?",
//                new Object[]{start}, new Object[]{end}, new RowMapper<MemoGroup>() {
//                    public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
//                        MemoGroup memoGroup = new MemoGroup();
//                        memoGroup.setId(resultSet.getInt("id"));
//                        memoGroup.setName(resultSet.getString("name"));
//                        memoGroup.setCreateTime(resultSet.getDate("created_time"));
//                        memoGroup.setModifyTime(resultSet.getDate("modify_time"));
//                        return memoGroup,
//                    }
//                });
//        logger.debug("query MemoGroup id={} result={}", id, memoGroups);
//        return memoGroups;
//    }

    //删除
    public int deleteMemo(int id) {
        int res = jdbcTemplate.update(
                "delete from memo_group where id = ?",
                id);
        logger.debug("query MemoGroup id={}",id);
        return res;
    }
}
