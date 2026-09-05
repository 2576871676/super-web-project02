package org.king.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.king.mapper.ClazzMapper;
import org.king.pojo.Clazz;
import org.king.pojo.ClazzQueryParam;
import org.king.pojo.PageResult;
import org.king.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1 分页参数，设置默认值避免空指针
        int page = clazzQueryParam.getPage() == null ? 1 : clazzQueryParam.getPage();
        int pageSize = clazzQueryParam.getPageSize() == null ? 10 : clazzQueryParam.getPageSize();

        //2 执行条件查询（try-with-resources 自动清理 PageHelper 的 ThreadLocal）
        try (Page<Clazz> p = PageHelper.startPage(page, pageSize)) {
            List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

            //3 为每个班级计算状态
            clazzList.forEach(this::setStatus);

            //4 封装分页结果
            return new PageResult<>(p.getTotal(), p.getResult());
        }
    }

    @Override
    public List<Clazz> findAll() {
        List<Clazz> list = clazzMapper.findAll();
        list.forEach(this::setStatus);
        return list;
    }

    @Override
    public Clazz getById(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        if (clazz != null) {
            setStatus(clazz);
        }
        return clazz;
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    /**
     * 根据当前日期计算班级状态
     * 未开班 / 在读 / 已结课
     */
    private void setStatus(Clazz clazz) {
        LocalDate today = LocalDate.now();
        if (clazz.getBeginDate() != null && today.isBefore(clazz.getBeginDate())) {
            clazz.setStatus("未开班");
        } else if (clazz.getEndDate() != null && today.isAfter(clazz.getEndDate())) {
            clazz.setStatus("已结课");
        } else {
            clazz.setStatus("在读");
        }
    }
}
