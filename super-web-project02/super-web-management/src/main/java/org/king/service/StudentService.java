package org.king.service;

import org.king.pojo.PageResult;
import org.king.pojo.Student;
import org.king.pojo.StudentQueryParam;

public interface StudentService {

    /**
     * 条件分页查询学员
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 根据ID查询学员
     */
    Student getById(Integer id);

    /**
     * 新增学员
     */
    void save(Student student);

    /**
     * 修改学员
     */
    void update(Student student);

    /**
     * 根据ID批量删除学员
     */
    void deleteByIds(String ids);

    /**
     * 违纪处理
     */
    void violation(Integer id, Integer score);
}
