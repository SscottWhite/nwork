package com.example.demo.service;

import com.example.demo.entity.NcUser;
import com.example.demo.otherstool.MD5Util;
import com.example.demo.otherstool.ObjectUtil;
import com.example.demo.repository.UserMapper;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public EcomResultDO<List<NcUser>> getList(NcUser ncUser){
        EcomResultDO<List<NcUser>> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        result.setData(userMapper.userList(ncUser));
        return result;
    }

    @TxcTransaction(timeout = 60*1000)
    public EcomResultDO<Boolean> insertOne(NcUser ncUser) throws Exception{
        EcomResultDO<Boolean> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        if(ObjectUtil.isEmpty(ncUser)
            ||ObjectUtil.isEmpty(ncUser.getUserNo())
            ||ObjectUtil.isEmpty(ncUser.getUserName())
            ||ObjectUtil.isEmpty(ncUser.getUserPass())
            ||ncUser.getUserRight() == null){
            throw new Exception("缺少参数,联系开发");
        }
        List<NcUser> list = userMapper.userList(ncUser);
        if(ObjectUtil.isNotEmpty(list)){
            throw new Exception("用户编号已存在");
        }
        ncUser.setUserPass(MD5Util.encodeMD5(ncUser.getUserPass()));
        if(userMapper.inserUser(ncUser) < 0){
            throw new Exception("添加失败,联系开发");
        }
        result.setData(Boolean.TRUE);
        return result;
    }



    public EcomResultDO<Boolean> login(NcUser ncUser){
        EcomResultDO<Boolean> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        if(ObjectUtil.isEmpty(ncUser)){
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"请填写用户账号",true);
            result.setData(Boolean.FALSE);
            return result;
        }
        if(ObjectUtil.isEmpty(ncUser.getUserPass())){
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"请填写密码",true);
            result.setData(Boolean.FALSE);
            return result;
        }
        if(ncUser.getUserNo() == null){
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"请填写账号",true);
            result.setData(Boolean.FALSE);
            return result;
        }
        try {
            ncUser.setUserPass(MD5Util.encodeMD5(ncUser.getUserPass()));
        } catch (NoSuchAlgorithmException e) {
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"程序报错,赶紧联系管理员",true);
            result.setData(Boolean.FALSE);
            return result;
        }
        List<NcUser> list = userMapper.userList(ncUser);

        if(ObjectUtil.isEmpty(list)){
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"用户不存在或密码不正确",true);
            result.setData(Boolean.FALSE);
            return result;
        }
        if(list.size()>1){
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"用户存在多个,请通知管理员",true);
            result.setData(Boolean.FALSE);
            return result;
        }
        result.setData(Boolean.TRUE);
        return result;
    }
}
