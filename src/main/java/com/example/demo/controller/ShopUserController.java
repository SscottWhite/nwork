package com.example.demo.controller;


import com.example.demo.entity.BdShop;
import com.example.demo.entity.ShopUser;
import com.example.demo.service.ShopUserService;
import com.example.demo.utils.EcomResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/shop_user")
@RestController
public class ShopUserController {

    @Autowired
    ShopUserService shopUserService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<List<ShopUser>> getList(@RequestBody Integer shopNo){
        EcomResultDO<List<ShopUser>> result = new EcomResultDO<>();
        List<ShopUser> list = shopUserService.getList(shopNo);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<List<ShopUser>> getNewList(@RequestBody BdShop bdShop){
        EcomResultDO<List<ShopUser>> result = new EcomResultDO<>();
        List<ShopUser> list = shopUserService.getNewList(bdShop.getUserNo(),bdShop.getShopNo());
        result.setData(list);
        return result;
    }
}
