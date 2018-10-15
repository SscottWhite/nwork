package com.example.demo.entity;

import lombok.Data;

@Data
public class NcStudents {
    private Integer id; //int(10) unsigned
    private String name; //varchar(40)
    private String age; //varchar(20)
    private String sex; //varchar(20)
}
