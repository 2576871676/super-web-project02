package org.king.mapper;

import org.apache.ibatis.annotations.*;
import org.king.pojo.Emp;
import org.king.pojo.EmpQueryParam;

import java.util.List;
import java.util.Map;

/**
 * 员工信息 Mapper
 */
@Mapper
public interface EmpMapper {

    /**
     * 分页条件查询（带姓名模糊搜索、性别、入职日期区间筛选）
     * 对应 XML 映射文件 EmpMapper.xml
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据ID查询员工
     */
    @Select("select * from emp where id = #{id}")
    Emp selectById(Integer id);

    /**
     * 修改员工信息
     */
    @Update("update emp set username=#{username}, password=#{password}, name=#{name}, gender=#{gender}, phone=#{phone}, " +
            "job=#{job}, salary=#{salary}, image=#{image}, entry_date=#{entryDate}, dept_id=#{deptId}, update_time=#{updateTime} " +
            "where id=#{id}")
    void update(Emp emp);

    /**
     * 根据ID删除员工
     */
    @Delete("delete from emp where id=#{ids}")
    void delete(Integer ids);

    /*
    * 统计各个职位员工人数
    * */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @Select("select count(*) from emp where phone=#{phone}")
    int findPhone(String phone);
    /*
    * 统计职工性别人数
    * */
    @MapKey("name")
    List<Map<String,Object>> countEmpGenderData();
}
