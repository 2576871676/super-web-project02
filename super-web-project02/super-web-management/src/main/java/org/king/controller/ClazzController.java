package org.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.Clazz;
import org.king.pojo.ClazzQueryParam;
import org.king.pojo.PageResult;
import org.king.pojo.Result;
import org.king.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 条件分页查询班级列表
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 查询所有班级（用于下拉选择）
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> list = clazzService.findAll();
        return Result.success(list);
    }

    /**
     * 根据ID查询班级
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询班级：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 新增班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级：{}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 修改班级
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 根据ID删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据ID删除班级：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
}
