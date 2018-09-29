package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.BdShop;
import com.example.demo.entity.BdShopDTO;
import com.example.demo.service.ShopService;
import com.example.demo.utils.EcomResultDO;
import com.example.demo.utils.FastJsonUtil;
import com.example.demo.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@RequestMapping("/shop")
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;


    final String  token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJqdGkiOi" +
            "IxNTM3NDMwNTEwMDU2N2U3NmI4ODcxYjQ0NDZhZDgxNjQxYzNiNWZmODkzMjgiLCJzdWIiOiJ7XCJhZG1" +
            "pbkZsYWdcIjowLFwiY29tcGFueU5hbWVcIjpcIuazouWPuOeZu-a1i-ivlV_pu4RcIixcImNvbXBhbnlOb1wi" +
            "OjY4LFwiZGVmaW5lUm9sZXNcIjpcIjBcIixcImRpc3RyaWJ1dG9yRnJlZXplRmxhZ1wiOlwiTlwiLFwiZGlzdHJp" +
            "YnV0b3JOYW1lXCI6XCLms6Llj7jnmbvmtYvor5Vf6buEXCIsXCJkaXN0cmlidXRvck5vXCI6NjAsXCJkaXN0cmlidXR" +
            "vclR5cGVcIjowLFwiZW1haWxOb1wiOlwiemhhbmcuZ3VvZmFuZ0Bib3NpZGVuZy5jb21cIixcIm5pY2tOYW1lXCI6XCLlvK" +
            "Dlm73oirNfMDAxNTc1XCIsXCJzdG9yZUxpc3RcIjpcIjI5LDIwNFwiLFwic3lzdGVtUm9sZXNcIjpcIjM5XCIsXCJ1c2VySWRcIj" +
            "ozMDUwOTQ0MzQ1MjUwNzQ1NTgxLFwidXNlck5hbWVcIjpcIuW8oOWbveiKs1wiLFwidXNlck5vXCI6XCIwMDE1NzVcIixcInVzZXJQ" +
            "aWN0dXJlXCI6XCJcIixcIndhcmVob3VzZU5hbWVcIjpcIlwiLFwid2FyZWhvdXNlTm9cIjoyLFwid2FyZWhvdXNlVHlwZVwiOjJ9IiwiaXNzI" +
            "joiMDowOjA6MDowOjA6MDoxIiwiaWF0IjoxNTM3NDMwNTEwLCJleHAiOjE1Mzc5MzA1MTB9.cEvgzGiJypkfK88NTpKBInqL98m94p0COHvP9O2GKC4";


    @RequestMapping(value = "/inset", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertList(@RequestBody BdShop bdShop){
        String str = bdShop.getShopNo().toString();
        EcomResultDO<Object> jsonStr = HttpUtil.httpPost(str,"/sync/stockLockingSync/toShopPage",token);
        Object o = jsonStr.getData();
        List<BdShopDTO> list = JSONArray.parseArray(o.toString(),BdShopDTO.class);


        List<BdShop> list1 = new ArrayList<>();
        for (BdShopDTO shop : list) {
            BdShop shop1 = new BdShop();
            shop1.setShopNo(Integer.parseInt(shop.getShopNo()));
            shop1.setShopName(shop.getShopName());
            list1.add(shop1);
        }
        log.error(list1.toString());
        return shopService.insertShopList(list1);
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<BdShop> getList(@RequestBody BdShop bdShop) {
        return shopService.getList(bdShop.getShopNo());
    }

    @RequestMapping(value = "/insertOne", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertOne(@RequestBody BdShop bdShop) {
        return shopService.insertShop(bdShop);
    }
}
