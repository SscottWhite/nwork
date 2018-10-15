package com.example.demo.repository;

import com.example.demo.entity.NcStudents;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NcStuMapper {

    //这个写法有问题,不过不清楚
    @Select("set @r := (select GROUP_CONCAT(tid ORDER BY tid asc) from nc_sc where id = #{id}); " +
            "select * from nc_students where id in " +
            "(select ac.id from (select id,GROUP_CONCAT(tid ORDER BY tid asc) name1 from nc_sc group by id) ac " +
            "where ac.name1 = @r)")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR),
            @Result(column = "age",property = "age",jdbcType = JdbcType.VARCHAR),
            @Result(column = "sex",property = "sex",jdbcType = JdbcType.VARCHAR)
    })
    List<NcStudents> get(@Param("id")Integer id);
}
