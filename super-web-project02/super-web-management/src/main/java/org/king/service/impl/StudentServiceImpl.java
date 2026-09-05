package org.king.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.king.mapper.StudentMapper;
import org.king.pojo.PageResult;
import org.king.pojo.Student;
import org.king.pojo.StudentQueryParam;
import org.king.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1 分页参数，设置默认值避免空指针
        int page = studentQueryParam.getPage() == null ? 1 : studentQueryParam.getPage();
        int pageSize = studentQueryParam.getPageSize() == null ? 10 : studentQueryParam.getPageSize();

        //2 执行条件查询（try-with-resources 自动清理 PageHelper 的 ThreadLocal）
        try (Page<Student> p = PageHelper.startPage(page, pageSize)) {
            studentMapper.list(studentQueryParam);
            //3 封装分页结果
            return new PageResult<>(p.getTotal(), p.getResult());
        }
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        if (student.getViolationCount() == null) {
            student.setViolationCount((short) 0);
        }
        if (student.getViolationScore() == null) {
            student.setViolationScore((short) 0);
        }
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteByIds(String ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);
    }
}
