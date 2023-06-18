package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.dao.IncomeDao;
import com.github.binarywang.demo.wx.miniapp.entity.IncomeEntity;
import com.github.binarywang.demo.wx.miniapp.service.IncomeService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl extends ServiceImpl<IncomeDao, IncomeEntity>
    implements IncomeService {
  @Override
  public int getShareNumber(int userId) {
    return count(new QueryWrapper<>(new IncomeEntity().setUserId(userId)));
  }

  @Override
  public List<IncomeEntity> getLatestByUserId(int userId, int count) {
    return list(new QueryWrapper<>(new IncomeEntity().setUserId(userId)).last("limit " + count));
  }
}
