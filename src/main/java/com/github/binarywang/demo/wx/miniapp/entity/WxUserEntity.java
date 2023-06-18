package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("t_wx_user")
public class WxUserEntity {
  private Integer id;

  private String openId;

  private String unionId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date registerDate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date lastLoginDate;

  private Short productFlag;

  private String phone;

  private String sessionKey;

  private int income;

  @TableField(exist = false)
  private List<Integer> productIds;
}
