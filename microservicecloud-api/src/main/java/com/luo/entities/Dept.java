package com.luo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data    //set设置值/get获取值注解
@NoArgsConstructor //无参数构造注解
@Accessors(chain=true) //链式访问
public class Dept {
    private Long deptno;
    private String dname;
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
}
