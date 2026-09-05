package org.king.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;   //结课时间范围-开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;     //结课时间范围-结束
}
