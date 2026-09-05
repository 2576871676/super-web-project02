package org.king.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.king.mapper.EmpExprMapper;
import org.king.mapper.EmpMapper;
import org.king.pojo.*;
import org.king.service.EmpLogService;
import org.king.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public LoginInfo login(Emp emp) {
        //1 调用mapper接口，根据员工用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2 判断是否存在： 存在，返回登录信息；不存在，返回null
        if (e!=null){
            log.info("登录成功：{}", e);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),"");
        }
        //3 登录失败
        return null;
    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1 分页参数（PageHelper），设置默认值避免空指针
        Integer page = empQueryParam.getPage() == null ? 1 : empQueryParam.getPage();
        Integer pageSize = empQueryParam.getPageSize() == null ? 10 : empQueryParam.getPageSize();
        PageHelper.startPage(page, pageSize);

        //2 执行查询（带条件，传递查询参数对象）
        List<Emp> empList = empMapper.list(empQueryParam);

        //3 解析结果，封装分页返回
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
/*
* 新增员工信息
* */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        //1 保存员工基本信息
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            int count= empMapper.findPhone(emp.getPhone());
            if(count!=0){
                throw new RuntimeException("手机号被使用---");
            }
            empMapper.insert(emp);
            //2 保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合
                exprList.forEach(empExpr ->
                        empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3 记录日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }

    }

    @Override
    public Emp selectById(Integer id) {
        //1 查询员工基本信息
        Emp emp = empMapper.selectById(id);
        //2 查询员工工作经历信息
        if (emp != null) {
            List<EmpExpr> exprList = empExprMapper.getByEmpId(id);
            emp.setExprList(exprList);
        }
        return emp;
    }

    @Override
    public void update(Emp emp) {
        //1 修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        int count=empMapper.findPhone(emp.getPhone());
        if(count!=0){
            throw new RuntimeException("手机号被使用---");
        }
        empMapper.update(emp);
        //2 修改员工工作经历信息：先删除旧的，再插入新的
        empExprMapper.deleteByEmpId(emp.getId());
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public void delete(Integer ids) {
        //1 删除员工基本信息
        empMapper.delete(ids);
        //2 删除员工关联的工作经历信息
        empExprMapper.deleteByEmpId(ids);
    }
}
