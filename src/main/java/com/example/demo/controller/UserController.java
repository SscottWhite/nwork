package com.example.demo.controller;

import com.example.demo.entity.NcUser;
import com.example.demo.otherstool.ObjectUtil;
import com.example.demo.service.UserService;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import com.example.demo.utils.ExcelSheetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<List<NcUser>> getList(@RequestBody NcUser ncUser){
        if(ObjectUtil.isEmpty(ncUser) || ObjectUtil.isEmpty(ncUser.getUserNo())){
            return new EcomResultDO<>(EcomResultCode.COMMON_FAIL,"缺少必要参数",false);
        }
        return userService.getList(ncUser);
    }

    @RequestMapping(value = "/insertOne",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<Boolean> insertOne(@RequestBody NcUser ncUser){
        EcomResultDO<Boolean> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        try{
            result = userService.insertOne(ncUser);
        }catch (Exception e){
            String fullStack = ExceptionUtils.getStackTrace(e);
            log.error(fullStack);
            result =  new EcomResultDO<>(EcomResultCode.COMMON_FAIL,fullStack, Boolean.FALSE);
        }
        return result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<Boolean> login(@RequestBody NcUser ncUser){
        EcomResultDO<Boolean> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        try{
            result = userService.login(ncUser);
        }catch (Exception e){
            String errorLog = ExceptionUtils.getStackTrace(e);
            log.error(errorLog);
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,errorLog,Boolean.FALSE);
        }
        return result;
    }

}
