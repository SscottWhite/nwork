package com.example.demo.service;

import com.example.demo.entity.BdShop;
import com.example.demo.repository.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopMapper shopMapper;

    //查找list
    public List<BdShop> getList(Integer shopNo) {
        return shopMapper.getList(shopNo);
    }
    //插入一条
    @Transactional(rollbackOn = Exception.class)
    public boolean insertShop(BdShop bdShop){
        int j =  shopMapper.insertDate(bdShop) ;
        return j>0;
    }
    //更新一条
    public boolean updateShop(BdShop bdShop){
        return shopMapper.updateShop(bdShop) > 0;
    }
    //插入多条
  //  @Transactional(rollbackOn = Exception.class)
    @TxcTransaction(timeout = 60*1000)
    public boolean insertShopList(List<BdShop> list){
        return shopMapper.insertShopList(list) > 0;
    }
}
