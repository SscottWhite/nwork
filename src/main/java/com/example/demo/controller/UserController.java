package com.example.demo.controller;

import com.example.demo.entity.NcUser;
import com.example.demo.otherstool.ObjectUtil;
import com.example.demo.otherstool.StringUtil;
import com.example.demo.service.UserService;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public EcomResultDO<Boolean> login(HttpServletRequest request,
                                       @RequestBody NcUser ncUser){
        EcomResultDO<Boolean> result = new EcomResultDO<>(EcomResultCode.TRUE,true);
        try{
            result = userService.login(ncUser);
            System.out.println(StringUtil.getIpAddress(request));
        }catch (Exception e){
            String errorLog = ExceptionUtils.getStackTrace(e);
            log.error(errorLog);
            result = new EcomResultDO<>(EcomResultCode.COMMON_FAIL,errorLog,Boolean.FALSE);
        }
        return result;
    }

    @RequestMapping(value = "/import",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<Boolean> importFile(@RequestBody MultipartFile file){
        EcomResultDO<Boolean> result = null;

        if (ObjectUtil.isEmpty(file)) {
            result = new  EcomResultDO<>(EcomResultCode.COMMON_FAIL,"请选择导入文件",Boolean.FALSE);
            result.setData(Boolean.FALSE);
            return result;
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        if (!suffix.equalsIgnoreCase("xlsx") && !suffix.equalsIgnoreCase("xls")) {
            result = new  EcomResultDO<>(EcomResultCode.COMMON_FAIL,"请选择EXCEL格式的文件!",Boolean.FALSE);
            result.setData(Boolean.FALSE);
            return result;
        }
        try {
            result = userService.insertList(file);
        }catch (Exception e){
            String logs = ExceptionUtils.getStackTrace(e);
            log.error(logs);
            result = new  EcomResultDO<>(EcomResultCode.COMMON_FAIL,logs,Boolean.FALSE);
            result.setData(Boolean.FALSE);
        }
        return  result;
    }

}
