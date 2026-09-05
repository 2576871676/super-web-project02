package org.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.ClazzCountOption;
import org.king.pojo.JobOption;
import org.king.pojo.Result;
import org.king.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数（柱状图）
     */
    @GetMapping("empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计职工性别人数（饼图）
     */
    @GetMapping("empGenderData")
    public Result getEmpGenderData(){
        log.info("统计职工性别人数");
        List<Map<String,Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }

    /**
     * 统计班级人数（学员信息统计-柱状图）
     */
    @GetMapping("studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        ClazzCountOption clazzCountOption = reportService.getStudentCountData();
        return Result.success(clazzCountOption);
    }

    /**
     * 统计学员学历分布（学员信息统计-饼图）
     */
    @GetMapping("studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历分布");
        List<Map<String,Object>> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }
}
