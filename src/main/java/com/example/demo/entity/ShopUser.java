package com.example.demo.entity;

import lombok.Data;

@Data
public class ShopUser {
    private Integer id;
    private Integer userShopNo;
    private String userName;
    private String shopName;

    private Integer newShopNo;
    private Integer newUserNo;
}
