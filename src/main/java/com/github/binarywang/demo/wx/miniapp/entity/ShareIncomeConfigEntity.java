package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("share_income_config")
public class ShareIncomeConfigEntity {
  private int id;
  private int minCount;
  private int maxCount;
  private int incomeAmount;
}
