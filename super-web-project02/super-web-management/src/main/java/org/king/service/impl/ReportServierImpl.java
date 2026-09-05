package org.king.service.impl;

import org.king.mapper.ClazzMapper;
import org.king.mapper.EmpMapper;
import org.king.mapper.StudentMapper;
import org.king.pojo.ClazzCountOption;
import org.king.pojo.JobOption;
import org.king.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServierImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        //1 调用MAPPER接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        //2 封装结果集并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        //1 调用MAPPER接口，获取每个班级的人数统计
        List<Map<String, Object>> list = clazzMapper.countStudentByClazz();

        //2 封装结果集并返回
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzCountOption(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegree();
    }
}
