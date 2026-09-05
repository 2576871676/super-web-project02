package org.king.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer degree;    //学历
    private Integer clazzId;   //班级ID
}
