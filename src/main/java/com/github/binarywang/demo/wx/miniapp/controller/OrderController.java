package com.github.binarywang.demo.wx.miniapp.controller;

import cn.hutool.core.lang.UUID;
import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.service.WxPayService;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import java.util.Date;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {

  @Resource WxPayService wxPayService;

  @GetMapping("/create")
  public PrepayWithRequestPaymentResponse createOrder(int userId) throws SystemException {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOrderStatus((short) 0);
    orderEntity.setDescription("pay test");
    orderEntity.setAmount(1);
    orderEntity.setCreateTime(new Date());
    orderEntity.setUserId(userId);
    orderEntity.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
    return wxPayService.prePay(orderEntity);
  }
}
