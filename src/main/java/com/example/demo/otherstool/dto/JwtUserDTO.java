package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *  该用于用于存放JSON的用户对象						
 * @ClassName JwtUserDTO
 * @author james
 * @date 2017年12月26日 下午2:13:40
 */
@Data
public class JwtUserDTO  implements Serializable{

	/**
	 *  * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
    private String userNo;//用户编号

    private String nickName;//用户昵称

    private String userName;//用户姓名

    private String userPicture;//用户头像

    private String gender;//用户性别

    private String mobileNo;//手机号码

    private String emailNo;//电子邮箱

    private Byte adminFlag;//管理员标志

    private String systemRoles;//系统权限

    private String defineRoles;//自定义权限(若设置自定义权限,系统权限无效)
    
    private String systemRoleName;//用户系统角色名称(列表展示时使用)
    
    private String defineRoleName;//用户自定义角色名称(列表展示时使用)

    private String storeList;//店铺查看权限(空:授权所有店铺; 0:空权限; 编号列表:授权指定店铺)
    
    private String storeName;//店铺名称(列表展示时使用)

    private Integer DistributorNo;//分销商编号

    private String distributorName;//分销商名称

    private Integer warehouseNo;//授权仓库编号

    private String warehouseName;//授权仓库名称
    
    private Byte warehouseType; //仓库类型

    private Integer companyNo;//公司编号
    
    private String companyName;//公司商名称
    
    private Byte distributorType; //分销商类型(0: 品牌商; 1: 分销商)
    
    private String DistributorFreezeFlag; //分销商冻结 (Y:已冻结  N：未冻结)
    
    private Byte distributorLevel; //分销商级别
    
    


    
}