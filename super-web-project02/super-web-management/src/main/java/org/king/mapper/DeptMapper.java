package org.king.mapper;

import org.apache.ibatis.annotations.*;
import org.king.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    /*
    * 查询所以部门数据*/
    //一：手动封装
  /*  @Results({
            @Result(column = "create_time",property ="createTime" ),
            @Result(column = "update_time",property = "updateTime")
    })*/
    //二：起别名
    @Select("select id,name,create_time,update_time from dept order by update_time desc")

    List<Dept> findAll();

    //根据Id删除
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);
    //新增部门
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
    //根据id查询部门
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    //修改部门
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}

