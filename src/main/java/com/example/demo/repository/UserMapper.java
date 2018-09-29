package com.example.demo.repository;

import com.example.demo.entity.NcUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.data.repository.query.Param;
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

    @Select("<script>" +
            "select " +
            "* " +
            "from " +
            "nc_user " +
            "where user_no = #{userNo}" +
            "<if test=\"userPass != null\">" +
            "and user_pass = #{userPass}" +
            "</if>" +
            "</script>")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "user_no",property = "userNo",jdbcType = JdbcType.INTEGER),
            @Result(column = "user_name",property = "userName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_pass",property = "userPass",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_right",property = "userRight",jdbcType = JdbcType.INTEGER),
            @Result(column = "ts",property = "ts",jdbcType = JdbcType.TIMESTAMP)
    })
    List<NcUser> userList(NcUser ncUser);


    @Select("select user_no from nc_user")
    @Results({
            @Result(column = "user_no",property = "userNo",jdbcType = JdbcType.INTEGER)
    })
    List<Integer> getListNos();


    @Insert("   <script> " +
            "   insert into nc_user" +
            "   (user_no" +
            "   ,user_name" +
            "   ,user_pass" +
            "   ,user_right)" +
            "   values " +
            "   <foreach collection=\"list\"   item=\"i\"  separator=\",\" >" +
            "   <trim  prefix=\"(\"   suffix=\")\" suffixOverrides=\",\" >"+
            "   #{i.userNo,jdbcType=INTEGER},   " +
            "   #{i.userName,jdbcType=VARCHAR}, " +
            "   #{i.userPass,jdbcType=VARCHAR}, " +
            "   #{i.userRight,jdbcType=INTEGER}," +
            "   </trim> " +
            "   </foreach> " +
            "   </script>")
    int insertList(@Param("list")List<NcUser> list);


    @Update("<script> " +
            "    update " +
            "    nc_user  set " +
            "    user_name=#{userName}" +
            "   , user_pass=#{userPass}" +
            "   , user_right=#{userRight}" +
            "    where " +
            "    user_no = #{userNo} " +
            "</script> ")
    int updateList(NcUser ncUser);

}
