package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_income")
@Accessors(chain = true)
public class IncomeEntity {
  private int id;
  private int userId;
  private int amount;
  private int orderId;
  private Date createTime;
  private int sharedUserId;
}
