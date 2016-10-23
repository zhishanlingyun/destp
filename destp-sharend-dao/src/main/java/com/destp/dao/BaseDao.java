package com.destp.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class BaseDao<T> extends SqlSessionDaoSupport {

    public T queryObjectById(String sql,Object obj){
        return this.getSqlSession().selectOne(sql,obj);
    }

    public List<T> queryList(String sql, Object obj){
        return this.getSqlSession().selectList(sql,obj);
    }

    public int create(String sql, Object obj){
        return this.getSqlSession().insert(sql,obj);
    }

    public int delete(String sql, Object obj){
        return this.getSqlSession().delete(sql,obj);
    }

    public int update(String sql, Object obj){
        return this.getSqlSession().update(sql,obj);
    }

    @Transactional
    public void batchInsert(List<T> list,String sql,Object obj){
        for(T t : list){
            this.getSqlSession().insert(sql,obj);
        }
    }

}
