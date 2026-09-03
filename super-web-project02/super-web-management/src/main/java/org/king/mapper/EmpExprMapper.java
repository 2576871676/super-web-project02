package org.king.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.king.pojo.EmpExpr;

import java.util.List;

/*
员工工作经历
*/
@Mapper
public interface EmpExprMapper {
    /*
    * 批量保存员工工作经历信息
    * */
    void insertBatch(List<EmpExpr> exprList);

    /*
    * 根据员工ID删除工作经历信息
    * */
    void deleteByEmpId(Integer empId);

    /*
    * 根据员工ID查询工作经历信息
    * */
    List<EmpExpr> getByEmpId(Integer empId);
}
