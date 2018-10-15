package com.example.demo.service;

import com.example.demo.entity.NcStudents;
import com.example.demo.repository.NcStuMapper;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NcService {

    @Autowired
    private NcStuMapper ncStuMapper;

    public EcomResultDO<List<NcStudents>> get(Integer id){
        EcomResultDO<List<NcStudents>> result = new EcomResultDO<>(EcomResultCode.TRUE,Boolean.TRUE);
        result.setData(ncStuMapper.get(id));
        return result;
    }

    public List<Integer> getI(Integer id){
        return ncStuMapper.getI(id);
    }
}
