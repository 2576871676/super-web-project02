package org.king.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer gender;   // 数据库中 gender 为 tinyint/int：1男，2女
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // 注意：MM是月份，mm是分钟
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
