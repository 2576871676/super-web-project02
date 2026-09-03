package org.king.service;

import org.king.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
    * 查询所以的部门信息*/
    List<Dept> findAll();


    //根据ID删除部门
    void deleteById(Integer id);

    //新增部门
    void add(Dept dept);

    //根据id查询部门
    Dept getById(Integer id);

    //修改部门
    void update(Dept dept);
}
