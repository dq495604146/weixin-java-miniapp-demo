package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;

public interface WxPayService {
  PrepayWithRequestPaymentResponse prePay(OrderEntity orderEntity) throws SystemException;
}
