package org.king.service;

import org.king.pojo.ClazzCountOption;
import org.king.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /*
    * 统计员工职位人数
    * */
    JobOption getEmpJobData();

    /*
     * 统计职工性别人数
     * */
    List<Map<String, Object>> getEmpGenderData();

    /*
     * 统计班级人数（学员信息统计-柱状图）
     * */
    ClazzCountOption getStudentCountData();

    /*
     * 统计学员学历分布（学员信息统计-饼图）
     * */
    List<Map<String, Object>> getStudentDegreeData();
}
