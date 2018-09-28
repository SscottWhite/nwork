package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BdShopDTO implements Serializable {
    private static final long serialVersionUID = -491837890960124036L;

    private String shopNo;//店铺编号

    private String siteName;//平台站点

    private String siteShortName;//站点简称

    private String shopName;//店铺名称

    private String shopShortName;//店铺简称

    private String shopAddress;//店铺地址

    private String mainAccount;//店铺主账号

    private String principal; //负责人

    private String telNo;//联系电话

    private Byte authorizeStatus;//授权状态（0：未授权 1：已授权 2：授权已过期）

    private String authorizeCode;//授权码

    private Date expireTime;//授权过期时间

    private String authorizeConfig;//授权配置

    private Byte isOrderDownload;//订单下载否

    private Byte isDeliveryUpload;//发货上传否

    private Byte isInventoryUpload;//库存上传否

    private Byte isSupportDownload;//售后下载否

    private Byte isTaobaoSupply;//淘宝供销否

    private String storeNo;//对应渠道店铺编号(线上订单同步到订单服务中心使用)

    private Byte enableStatus;//启用状态(0:禁用; 1:启用)

    private Integer distributorNo;//分销商编号

    private Integer companyNo;//公司编号

    private Date createTime;//创建时间

    private String createUser;//创建人员

    private Date updateTime;//修改时间

    private String updateUser;//修改人员

    private Date ts;//

    private Integer receiveAddrNo;//卖家收货地址编号

    private String receiveAddr;//卖家收货地址

    private String mobileNo;//手机号码

    private String postCode;//邮政编码

    private String vipSupplyNo;//唯品会供应商编号

    private String jdUnboundSubscribe;//京东无界订购关系

    private String siteMerchantCode; //平台商家编码

    private Integer isLockOnly;   //锁定库存否(0:否;1:是) => 只同步锁定库存

    private Integer isAligenius;//AG授权否(0:否;1:是)

    private String storeList;

    private String omniAuthorizeCode;

    private Integer omniAuthorizeStatus;//新零售授权状态(0:未授权; 1:已授权; 2:授权已过期)

    private Date omniExpireTime;//新零售过期时间
}
