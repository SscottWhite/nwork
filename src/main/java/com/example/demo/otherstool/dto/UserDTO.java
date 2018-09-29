package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDTO
 * @Description: TODO 用户 DTO
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2016年3月14日 下午3:17:58
 * 
 */
@Data
public class UserDTO implements Serializable {

  private static final long serialVersionUID = 845858644789842373L;
  private Long userId;
  private String userNo;
  private String nickName;
  private String userName;
  private String password;
  private String gender;
  private String idCardNo;
  private Byte dataAuthority;
  // 体系架构调整
  // private Long orgId;
  // private String orgNo; // 组织编号（渠道中心）
  // private String orgName; // 组织名称（渠道中心）
  private Long deptId; // HR 部门主键
  private String deptNo; // HR 部门编号
  private String deptName; // HR 部门名称
  private String mobileNo;
  private String emailNo;
  private Date birthday;
  private Long province;
  private Long city;
  private Long county;
  private Long town;
  private String address;
  private Long userType;
  private String remark;
  private String validFlag;
  private String lockFlag;
  private Date createDate;
  private Date ts;
  
  private String storeNo; // 营业员登录网点信息
  private String custNo; // 营业员登录客户信息
  private String corpNo; // 营业员登录公司信息
  private String postNo; // 岗位编号
  private String postName; // 岗位名称
}
