package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("t_wx_user")
public class WxUserEntity {
  private Integer id;

  private String openid;

  private String nickName;

  private String avatarUrl;

  private Date registerDate;

  private Date lastLoginDate;

  private short productFlag;

  @TableField(select = false, exist = false)
  private String code;
}
