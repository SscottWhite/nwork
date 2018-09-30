package com.example.demo.service;

import com.example.demo.entity.ShopUser;
import com.example.demo.repository.ShopUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopUserService {

    @Autowired
    ShopUserMapper shopUserMapper;

    public List<ShopUser> getList(Integer shopNo){
        return shopUserMapper.getList(shopNo);
    }

}
