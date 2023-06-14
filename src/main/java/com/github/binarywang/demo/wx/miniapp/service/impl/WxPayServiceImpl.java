package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.constant.ErrorEnum;
import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.service.OrderService;
import com.github.binarywang.demo.wx.miniapp.service.UserService;
import com.github.binarywang.demo.wx.miniapp.service.WxPayService;
import com.github.binarywang.demo.wx.miniapp.utils.WxPayUtil;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxPayServiceImpl implements WxPayService {
  @Value("${wx.miniapp.notifyUrl}")
  private String notifyUrl;

  @Resource WxPayUtil wxPayUtil;

  @Resource UserService userService;

  @Resource OrderService orderService;

  @Override
  public PrepayWithRequestPaymentResponse prePay(OrderEntity orderEntity) throws SystemException {
    WxUserEntity wxUserEntity = userService.queryById(orderEntity.getUserId());
    if (wxUserEntity == null) {
      throw SystemException.buildSystemException(ErrorEnum.UserNotFound);
    }
    JsapiServiceExtension jsapiServiceExtension = wxPayUtil.getJsapiServiceExtension();
    PrepayRequest prepayRequest = new PrepayRequest();
    prepayRequest.setOutTradeNo(orderEntity.getOutTradeNo());
    prepayRequest.setDescription(orderEntity.getDescription());
    prepayRequest.setNotifyUrl(notifyUrl);
    Amount amount = new Amount();
    amount.setTotal(orderEntity.getAmount());
    prepayRequest.setAmount(amount);
    Payer payer = new Payer();
    payer.setOpenid(wxUserEntity.getOpenid());
    prepayRequest.setPayer(payer);
    try {
      PrepayWithRequestPaymentResponse response =
          jsapiServiceExtension.prepayWithRequestPayment(prepayRequest);
      orderService.createOrder(orderEntity);
      return response;
    } catch (HttpException e) {
      log.error("发送HTTP请求失败", e);
    } catch (ServiceException e) {
      log.error("微信服务返回错误", e);
    } catch (MalformedMessageException e) {
      log.error("MalformedMessageException", e);
    }
    throw SystemException.buildSystemException(ErrorEnum.PrePayError);
  }
}
