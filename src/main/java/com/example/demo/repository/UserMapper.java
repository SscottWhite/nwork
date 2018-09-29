package com.example.demo.repository;

import com.example.demo.entity.NcUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Insert("   insert into nc_user" +
            "   (user_no" +
            "   ,user_name" +
            "   ,user_pass" +
            "   ,user_right)" +
            "   values" +
            "   (#{userNo}" +
            "   ,#{userName}" +
            "   ,#{userPass}" +
            "   ,#{userRight})")
    int inserUser(NcUser ncUser);

    @Select("select * from nc_user where user_no = #{userNo}")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "user_no",property = "userNo",jdbcType = JdbcType.INTEGER),
            @Result(column = "user_name",property = "userName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_pass",property = "userPass",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_right",property = "userRight",jdbcType = JdbcType.INTEGER),
            @Result(column = "ts",property = "ts",jdbcType = JdbcType.TIMESTAMP)
    })
    List<NcUser> userList(@Param("userNo") Integer userNo);
}
