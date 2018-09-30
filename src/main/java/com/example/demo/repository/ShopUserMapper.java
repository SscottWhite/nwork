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
}
