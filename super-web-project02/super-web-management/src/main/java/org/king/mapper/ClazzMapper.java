package org.king.mapper;

import org.apache.ibatis.annotations.*;
import org.king.pojo.Clazz;
import org.king.pojo.ClazzQueryParam;

import java.util.List;
import java.util.Map;

/**
 * 班级信息 Mapper
 */
@Mapper
public interface ClazzMapper {

    /**
     * 条件分页查询班级列表（对应 XML）
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 查询所有班级
     */
    @Select("select * from clazz order by update_time desc")
    List<Clazz> findAll();

    /**
     * 根据ID查询班级
     */
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 新增班级
     */
    @Insert("insert into clazz (name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /**
     * 修改班级
     */
    @Update("update clazz set name=#{name}, room=#{room}, begin_date=#{beginDate}, end_date=#{endDate}, " +
            "master_id=#{masterId}, subject=#{subject}, update_time=#{updateTime} where id=#{id}")
    void update(Clazz clazz);

    /**
     * 根据ID删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 统计每个班级的人数（用于学员信息统计-班级人数柱状图）
     */
    @Select("select c.name AS name, count(s.id) AS num from clazz c left join student s on s.clazz_id = c.id group by c.id, c.name order by num")
    List<Map<String, Object>> countStudentByClazz();
}
