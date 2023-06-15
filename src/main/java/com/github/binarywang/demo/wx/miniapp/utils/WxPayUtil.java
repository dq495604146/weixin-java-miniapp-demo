package com.github.binarywang.demo.wx.miniapp.utils;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxPayUtil {

  @Value("${wx.miniapp.merchantId}")
  public String merchantId;

  @Value("${wx.miniapp.privateKeyPath}")
  public String privateKeyPath;

  @Value("${wx.miniapp.merchantSerialNumber}")
  public String merchantSerialNumber;

  @Value("${wx.miniapp.apiV3key}")
  public String apiV3key;

  @Getter private Config config;

  @Getter private JsapiServiceExtension jsapiServiceExtension;

  @PostConstruct
  public void init() {
    // 初始化商户配置
    config =
        new RSAAutoCertificateConfig.Builder()
            .merchantId(merchantId)
            .privateKeyFromPath(privateKeyPath)
            .merchantSerialNumber(merchantSerialNumber)
            .apiV3Key(apiV3key)
            .build();
    // 初始化服务
    jsapiServiceExtension =
        new JsapiServiceExtension.Builder()
            .config(config)
            .signType("RSA") // 不填默认为RSA
            .build();
  }
}
