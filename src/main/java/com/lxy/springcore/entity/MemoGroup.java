package com.lxy.springcore.entity;

import java.util.Date;

/**
 * @author LXY
 * @email 403824215@qq.com
 * @date 2018/7/23 15:32
 *
 * 便签组
 */
//1..实体类
public class MemoGroup {

    //便签组id
    private Integer id;

    //便签组名称
    private String name;

    //便签组创建事件
    private Date createTime;

    //修改事件
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "MemoGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
