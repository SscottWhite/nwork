package com.example.demo.repository;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartzMapper {

    @Update("  ")
    int updateQuartz();
}
