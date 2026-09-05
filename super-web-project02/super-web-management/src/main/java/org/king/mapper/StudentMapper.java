package org.king.mapper;

import org.apache.ibatis.annotations.*;
import org.king.pojo.Student;
import org.king.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

/**
 * 学员信息 Mapper
 */
@Mapper
public interface StudentMapper {

    /**
     * 条件分页查询学员列表（对应 XML）
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 根据ID查询学员
     */
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 新增学员
     */
    @Insert("insert into student (name, no, gender, phone, id_card, is_college, address, degree, graduation_date, " +
            "clazz_id, violation_count, violation_score, create_time, update_time) " +
            "values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, " +
            "#{clazzId}, #{violationCount}, #{violationScore}, #{createTime}, #{updateTime})")
    void insert(Student student);

    /**
     * 修改学员
     */
    @Update("update student set name=#{name}, no=#{no}, gender=#{gender}, phone=#{phone}, id_card=#{idCard}, " +
            "is_college=#{isCollege}, address=#{address}, degree=#{degree}, graduation_date=#{graduationDate}, " +
            "clazz_id=#{clazzId}, update_time=#{updateTime} where id=#{id}")
    void update(Student student);

    /**
     * 根据ID批量删除学员（ids为逗号分隔字符串，如 1,2,3）
     */
    @Delete("delete from student where id in (${ids})")
    void deleteByIds(@Param("ids") String ids);

    /**
     * 违纪处理：违纪次数+1，违纪扣分累加
     */
    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score}, update_time = now() where id = #{id}")
    void violation(@Param("id") Integer id, @Param("score") Integer score);

    /**
     * 统计学员学历分布（用于学员信息统计-学历饼图）
     */
    @Select("select (case when degree=1 then '初中' when degree=2 then '高中' when degree=3 then '大专' " +
            "when degree=4 then '本科' when degree=5 then '硕士' when degree=6 then '博士' else '其他' end) name, " +
            "count(*) value from student group by degree order by degree")
    List<Map<String, Object>> countStudentDegree();
}
