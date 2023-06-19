package com.github.binarywang.demo.wx.miniapp.controller;

import cn.hutool.core.lang.UUID;
import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.service.WxPayService;
import com.github.binarywang.demo.wx.miniapp.utils.PermissionManager;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import java.util.Date;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {

  @Resource WxPayService wxPayService;

  @PostMapping("/create")
  public PrepayWithRequestPaymentResponse createOrder(@RequestBody OrderEntity orderEntity)
      throws SystemException {
    orderEntity.setOrderStatus((short) 0);
    orderEntity.setCreateTime(new Date());
    orderEntity.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
    PermissionManager permissionManager = new PermissionManager();
    orderEntity.setProductFlag(
        permissionManager.productIdList2productFlag(orderEntity.getProductIds()));
    log.info("create order {}", orderEntity);
    return wxPayService.prePay(orderEntity);
  }
}
