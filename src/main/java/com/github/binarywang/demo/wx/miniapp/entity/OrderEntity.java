package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_order")
@Accessors(chain = true)
public class OrderEntity {
  private int id;
  private int userId;
  private int totalMoney;
  private Date createTime;
  private Date updateTime;
  private short orderStatus;
  private String outTradeNo;
  private String description;
  private short productFlag;
  private int shareUserId;

  @TableField(exist = false)
  private List<Integer> productIds;
}
