package com.example.demo.repository;

import com.example.demo.entity.ShopUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopUserMapper {

    @Select("select a.id id" +
            ",a.user_shop_no" +
            ",b.shop_name" +
            ",a.user_name" +
            " from nc_user a inner join nc_shop b" +
            " on a.user_shop_no = b.shop_no " +
            "where a.user_shop_no = #{shopNo}")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "user_shop_no",property = "userShopNo",jdbcType = JdbcType.INTEGER),
            @Result(column = "shop_name",property = "shopName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_name",property = "userName",jdbcType = JdbcType.VARCHAR)
    })
    List<ShopUser> getList(@Param("shopNo")Integer shopNo);


    @Select("<script>" +
            " select " +
            "   <![CDATA[ " +
            "   @userno := (case user_no" +
            "        when #{userNo} then 1234 " +
            "        when #{shopNo} then 12345" +
            "        else 123456 end) " +
            "   ]]> " +
            "   , if(@userno = 1234,@userno - 1,0) newUserNo " +
            "   , if(@userno = 12345,@userno - 1 ,0) newShopNo " +
            "  from nc_user " +
            "</script>")
    @Results({
            @Result(column = "newUserNo",property = "newUserNo",jdbcType = JdbcType.INTEGER),
            @Result(column = "newShopNo",property = "newShopNo",jdbcType = JdbcType.INTEGER)
    })
    List<ShopUser> getNewList(@Param("userNo") Integer userNo,
                              @Param("shopNo") Integer shopNo);
}
