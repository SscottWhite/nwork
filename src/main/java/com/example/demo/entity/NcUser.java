package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NcUser {
    private Integer id;
    private Integer userNo;
    private String userName;
    private String userPass;
    private Integer userRight;//用户角色权限(0,管理员;1,普通用户,2,普通会员,3,黄金会员,4,普通年费会员,5,黄金年费会员)
    private Date ts;
}
