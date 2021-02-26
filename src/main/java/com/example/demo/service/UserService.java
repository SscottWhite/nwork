package com.example.demo.service;

import com.example.demo.entity.NcUser;
import com.example.demo.otherstool.MD5Util;
import com.example.demo.otherstool.ObjectUtil;
import com.example.demo.repository.UserMapper;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import com.example.demo.utils.ExcelUtil;
//import com.taobao.txc.client.aop.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public EcomResultDO<List<NcUser>> getList(NcUser ncUser){
        EcomResultDO<List<NcUser>> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        result.setData(userMapper.userList(ncUser));
        return result;
    }

   // @TxcTransaction(timeout = 60*1000)
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

    @Transactional(rollbackOn = Exception.class )
    public EcomResultDO<Boolean> insertList(MultipartFile file) throws Exception{
        EcomResultDO<Boolean> result =new EcomResultDO<>(EcomResultCode.TRUE,true);
        String errorMessage = "";
        List<NcUser> list = new ArrayList<>();
        //解析file
        List<List<String>> datas = ExcelUtil.parse(file);
        if (datas.size() < 2) {
            result = new  EcomResultDO<>(EcomResultCode.COMMON_FAIL,"导入失败，表格数据为空，请添加数据!",Boolean.FALSE);
            result.setData(Boolean.FALSE);
            return result;
        }
        // 导入excel校验表头是否一致
        if (datas != null && !datas.isEmpty()) {
            for (int i = 0; i < 1; i++) {
                List<String> row = datas.get(i);
                if (row != null && !row.isEmpty()) {
                    boolean impflag = "用户编号".equals(row.get(0))
                            && "用户昵称".equals(row.get(1))
                            && "用户密码".equals(row.get(2))
                            && "用户资格".equals(row.get(3));
                    if (!impflag) {
                        errorMessage += "导入失败，您使用的导入模板有误，请核查！" +
                                "表头:[用户编号][用户昵称][用户密码][用户资格]";
                        result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,errorMessage,Boolean.FALSE);
                        result.setData(Boolean.FALSE);
                        return result;
                    }
                }
            }
        }
        for (int j = 1; j < datas.size(); j++) {
            NcUser ncUser = new NcUser();
            List<String> listData = datas.get(j);
            if(listData.get(0) == null){
                result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"第"+j+"行,用户编号为空",Boolean.FALSE);
                result.setData(Boolean.FALSE);
                return result;
            }
            if(listData.get(1) == null){
                result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"第"+j+"行,用户昵称为空",Boolean.FALSE);
                result.setData(Boolean.FALSE);
                return result;
            }
            if(listData.get(2) == null){
                result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"第"+j+"行,用户密码为空",Boolean.FALSE);
                result.setData(Boolean.FALSE);
                return result;
            }
            if(listData.get(3) == null){
                result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"第"+j+"行,用户资格为空",Boolean.FALSE);
                result.setData(Boolean.FALSE);
                return result;
            }
            ncUser.setUserNo(Integer.parseInt(listData.get(0)));
            ncUser.setUserName(listData.get(1));
            ncUser.setUserPass(MD5Util.encodeMD5(listData.get(2)));
            ncUser.setUserRight(Integer.parseInt(listData.get(3)));
            list.add(ncUser);
        }

        if(list == null || list.size() <= 0){
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"导入数据为空！",Boolean.FALSE);
            result.setData(Boolean.FALSE);
            return result;
        }

        Map<Integer,NcUser> map = new HashMap<>();
        for (NcUser user : list) {
            map.put(user.getUserNo(),user);
        }

        List<Integer> listNos = userMapper.getListNos();
        List<NcUser> listUp = new ArrayList<>();//更新
        List<NcUser> listSert = new ArrayList<>();//插入

        if(ObjectUtil.isEmpty(listNos)){
            if(userMapper.insertList(list) < 0 ){
                throw new Exception("插入失败,赶紧联系人");
            }
        }else{
            Iterator<Map.Entry<Integer,NcUser>> it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Integer, NcUser> entry=it.next();
                if(listNos.contains(entry.getKey())){
                    listUp.add(entry.getValue());
                }else{
                    listSert.add(entry.getValue());
                }
            }

            if(ObjectUtil.isNotEmpty(listSert)) {
                if (userMapper.insertList(listSert) < 0) {
                    throw new Exception("插入失败,赶紧联系人");
                }
            }
            if(ObjectUtil.isNotEmpty(listUp)){
                for (NcUser user : listUp) {
                    if(userMapper.updateList(user) < 0){
                        throw new Exception("更新失败,赶紧联系人");
                    }
                }
            }

        }
        result.setData(Boolean.TRUE);
        return result;
    }

}
