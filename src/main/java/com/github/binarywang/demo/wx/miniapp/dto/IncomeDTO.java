package com.github.binarywang.demo.wx.miniapp.dto;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IncomeDTO {
  private int userId;
  private int shareNumber;
  private int totalMoney;
  private List<String> sharedUserPhoneList;
}
