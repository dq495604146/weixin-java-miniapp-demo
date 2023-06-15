package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.service.OrderService;
import com.github.binarywang.demo.wx.miniapp.service.UserService;
import com.github.binarywang.demo.wx.miniapp.utils.WxPayUtil;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/wx/pay")
public class WxPayController {

  @Resource private WxPayUtil wxPayUtil;

  @Resource OrderService orderService;

  @Resource UserService userService;

  @PostMapping("/notify")
  public Map<String, String> payNotify(
      @RequestHeader(value = "Wechatpay-Serial", defaultValue = "") String serialNumber,
      @RequestHeader(value = "Wechatpay-Nonce", defaultValue = "") String nonce,
      @RequestHeader(value = "Wechatpay-Signature", defaultValue = "") String signature,
      @RequestHeader(value = "Wechatpay-Timestamp", defaultValue = "") String timestamp,
      @RequestBody String body) {
    RequestParam requestParam =
        new RequestParam.Builder()
            .serialNumber(serialNumber)
            .nonce(nonce)
            .signature(signature)
            .timestamp(timestamp)
            .body(body)
            .build();
    NotificationParser parser = new NotificationParser((NotificationConfig) wxPayUtil.getConfig());
    Transaction transaction = parser.parse(requestParam, Transaction.class);
    String outTradeNo = transaction.getOutTradeNo();
    int state = Transaction.TradeStateEnum.SUCCESS == transaction.getTradeState() ? 1 : 2;
    OrderEntity order = orderService.getOrderByOutTradeNo(outTradeNo);
    orderService.updateOrder(order.setOrderStatus((short) state));
    if (state == 1) {
      WxUserEntity wxUserEntity = new WxUserEntity();
      wxUserEntity.setId(order.getUserId());
      wxUserEntity.setProductFlag(order.getProductFlag());
      userService.updateWxUserEntity(wxUserEntity);
    }
    return new HashMap<>();
  }
}
