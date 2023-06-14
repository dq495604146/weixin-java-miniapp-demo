package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;

public interface OrderService {
  void createOrder(OrderEntity orderEntity);

  void updateOrder(OrderEntity orderEntity);
}
