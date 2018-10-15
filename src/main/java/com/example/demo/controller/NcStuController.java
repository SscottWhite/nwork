package com.example.demo.controller;

import com.example.demo.entity.NcStudents;
import com.example.demo.service.NcService;
import com.example.demo.utils.EcomResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(value = "/")
@RestController
public class NcStuController {

    @Autowired
    private NcService ncService;

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public EcomResultDO<List<NcStudents>> get(HttpServletRequest request,
                                              @RequestBody NcStudents students){

        EcomResultDO<List<NcStudents>> result = ncService.get(students.getId());
        return result;
    }
}
