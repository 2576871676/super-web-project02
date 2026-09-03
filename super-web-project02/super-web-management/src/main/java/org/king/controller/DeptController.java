package org.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.Dept;
import org.king.pojo.Result;
import org.king.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    // private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)

    //获取部门信息
    @GetMapping
    public Result list(){

        //System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 根据ID删除部门
     */
    @DeleteMapping
    public Result delete(@RequestParam(required = false) Integer id) {
        log.info("根据部门ID删除：{}", id);
        if (id == null) {
            return Result.error("删除失败：缺少部门ID参数");
        }
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        // System.out.println("新增部门"+dept);
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    //根据Id查询部门
/*    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        System.out.println("查询id部门："+deptId);
        return Result.success();
    }*/
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        // System.out.println("查询id部门："+id);
        log.info("通过id查询部门： {}",id);
        Dept dept= deptService.getById(id);
        return Result.success(dept);
    }
    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
       // System.out.println("修改部："+dept);
        log.info("修改部门： {}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
