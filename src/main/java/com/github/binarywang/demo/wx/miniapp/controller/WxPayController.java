package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.entity.IncomeEntity;
import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.entity.ShareIncomeConfigEntity;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.service.IncomeService;
import com.github.binarywang.demo.wx.miniapp.service.OrderService;
import com.github.binarywang.demo.wx.miniapp.service.ShareIncomeConfigService;
import com.github.binarywang.demo.wx.miniapp.service.WxUserService;
import com.github.binarywang.demo.wx.miniapp.utils.WxPayUtil;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import java.util.*;
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

  @Resource WxUserService wxUserService;

  @Resource IncomeService incomeService;

  @Resource ShareIncomeConfigService shareIncomeConfigService;

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
      // 开通用户权限
      WxUserEntity wxUserEntity = new WxUserEntity();
      wxUserEntity.setId(order.getUserId());
      wxUserEntity.setProductFlag(order.getProductFlag());
      wxUserService.updateWxUserEntity(wxUserEntity);
      // 用户通过分享链接进入, 分享者获得收益
      int shareUserId = order.getShareUserId();
      if (shareUserId != 0) {
        // 写income表
        int shareNumber = incomeService.getShareNumber(shareUserId);
        // 根据收益规则计算收益
        int income = computeIncome(shareNumber + 1);
        incomeService.save(
            new IncomeEntity()
                .setUserId(shareUserId)
                .setAmount(income)
                .setOrderId(order.getId())
                .setCreateTime(new Date())
                .setSharedUserId(order.getUserId()));
        // 分享者收益更新
        WxUserEntity shareUser = wxUserService.queryById(shareUserId);
        wxUserService.updateWxUserEntity(shareUser.setIncome(shareUser.getIncome() + income));
      }
    }
    return new HashMap<>();
  }

  private int computeIncome(int count) {
    if (count < 1) {
      return 0;
    }
    List<ShareIncomeConfigEntity> shareConfig = shareIncomeConfigService.list();
    ShareIncomeConfigEntity shareIncomeConfigEntity =
        shareConfig.stream()
            .filter(config -> count >= config.getMinCount() && count <= config.getMaxCount())
            .findFirst()
            .get();
    return shareIncomeConfigEntity.getIncomeAmount();
  }
}
