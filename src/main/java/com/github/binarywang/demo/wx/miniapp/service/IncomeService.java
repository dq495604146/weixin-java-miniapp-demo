package com.github.binarywang.demo.wx.miniapp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.demo.wx.miniapp.entity.IncomeEntity;
import java.util.List;

public interface IncomeService extends IService<IncomeEntity> {
  int getShareNumber(int userId);

  List<IncomeEntity> getLatestByUserId(int userId, int count);
}
