package org.king.service;

import org.king.pojo.Clazz;
import org.king.pojo.ClazzQueryParam;
import org.king.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    /**
     * 条件分页查询班级
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 查询所有班级
     */
    List<Clazz> findAll();

    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);

    /**
     * 新增班级
     */
    void save(Clazz clazz);

    /**
     * 修改班级
     */
    void update(Clazz clazz);

    /**
     * 根据ID删除班级
     */
    void deleteById(Integer id);
}
