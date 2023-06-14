package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName("t_order")
public class OrderEntity {
  private int id;
  private int userId;
  private int amount;
  private Date createTime;
  private Date updateTime;
  private short orderStatus;
  private String outTradeNo;
  private String description;
}
