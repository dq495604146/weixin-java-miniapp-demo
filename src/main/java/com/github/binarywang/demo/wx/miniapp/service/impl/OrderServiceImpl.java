package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.dao.OrderDao;
import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
  @Override
  public void createOrder(OrderEntity orderEntity) {
    save(orderEntity);
  }

  @Override
  public void updateOrder(OrderEntity orderEntity) {
    updateById(orderEntity);
  }

  @Override
  public OrderEntity getOrderByOutTradeNo(String outTradeNo) {
    return getOne(new QueryWrapper<>(new OrderEntity().setOutTradeNo(outTradeNo)));
  }
}
