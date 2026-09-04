package org.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.JobOption;
import org.king.pojo.Result;
import org.king.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;
    /*
    * 统计员工职位人数
    * */
    @GetMapping("empJobData")
    public Result getEmpJobData(){
        log.info("员工职位统计");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    /*
    * 统计职工性别人数
    * */
    @GetMapping("empGenderData")
    public Result getEmpGenderData(){
        log.info("员工性别统计");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
}
