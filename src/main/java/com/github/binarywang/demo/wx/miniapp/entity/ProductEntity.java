package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_product")
@Accessors(chain = true)
public class ProductEntity {
  private int id;
  private String name;
  private String description;
  private int originPrice;
  private int realPrice;
}
