package com.example.demo.service;

import com.example.demo.entity.NcUser;
import com.example.demo.repository.UserMapper;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public EcomResultDO<List<NcUser>> getList(NcUser ncUser){
        EcomResultDO<List<NcUser>> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        result.setData(userMapper.userList(ncUser.getUserNo()));
        return result;
    }
}
