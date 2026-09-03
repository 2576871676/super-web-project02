package org.king.service;

import org.king.pojo.Emp;
import org.king.pojo.EmpQueryParam;
import org.king.pojo.PageResult;

public interface EmpService {
    /*
    page分页查询
    @Param page 页码
    @Param pageSize 每页数
    @Param gender 性别筛选（可选）
    @Param name 姓名模糊搜索（可选）
    * */
    //PageResult<Emp> page(Integer page, Integer pageSize, Integer gender, String name);

    PageResult<Emp> page(EmpQueryParam empQueryParam);
    /*
    * 新增用户
    * */
    void save(Emp emp);

    Emp selectById(Integer id);

    void update(Emp emp);

    void delete(Integer ids);

}
