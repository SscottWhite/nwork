package com.example.demo.repository;

import com.example.demo.entity.BdShop;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {
    @Select("select * from bd_shop where shop_no = #{shopNo}")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "shop_no", property = "shopNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "shop_name", property = "shopName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "shop_type", property = "shopType", jdbcType = JdbcType.INTEGER),
            @Result(column = "ts",property = "ts", jdbcType = JdbcType.TIMESTAMP)
    })
    List<BdShop> getList(@Param("shopNo")Integer shopNo);

    @Insert("   INSERT INTO bd_shop" +
            "   (shop_no , " +
            "   shop_name) " +
            "   VALUES " +
            "   (#{shopNo}, " +
            "   #{shopName})")
    int insertDate(BdShop bdShop);

    @Update(" update " +
            " bd_shop " +
            " set " +
            " shop_name = #{shopName} " +
            " where " +
            " shop_no = #{shopNo}")
    int updateShop(BdShop bdShop);


    @Insert("   <script> " +
            "   insert into bd_shop" +
            "   (shop_no ,shop_name) " +
            "   VALUES" +
            "   <foreach collection=\"list\" item=\"i\"   separator=\",\" >" +
            "   <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >"+
            "   #{i.shopNo,jdbcType=INTEGER}," +
            "   #{i.shopName,jdbcType=VARCHAR}," +
            "   </trim> " +
            "   </foreach> " +
            "   </script>")
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insertShopList(@Param("list") List<BdShop> list);
}
