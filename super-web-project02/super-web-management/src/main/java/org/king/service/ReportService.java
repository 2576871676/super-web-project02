package org.king.service;

import org.king.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /*
    * 统计员工人数
    * */
    JobOption getEmpJobData();

    /*
     * 统计职工性别人数
     * */
    List<Map<String, Object>> getEmpGenderData();
}
