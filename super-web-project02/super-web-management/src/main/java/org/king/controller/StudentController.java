package org.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.PageResult;
import org.king.pojo.Result;
import org.king.pojo.Student;
import org.king.pojo.StudentQueryParam;
import org.king.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 条件分页查询学员列表
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询学员：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员：{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 新增学员
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员：{}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 修改学员
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员：{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 根据ID批量删除学员（ids为单个ID或逗号分隔字符串，如 1,2,3）
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("批量删除学员ID：{}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪处理：id={}, score={}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
