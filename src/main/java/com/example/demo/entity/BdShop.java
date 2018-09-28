package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BdShop {
    private Integer id;
    private Integer shopNo;
    private String  shopName;
    private Integer shopType;
    private Date ts;
}
