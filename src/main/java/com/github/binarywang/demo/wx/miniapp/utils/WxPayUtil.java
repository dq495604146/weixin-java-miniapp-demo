package com.github.binarywang.demo.wx.miniapp.utils;

import com.wechat.pay.java.core.RSAConfig;
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

  @Value("${wx.miniapp.wechatPayCertificatePath}")
  public String wechatPayCertificatePath;

  @Getter public JsapiServiceExtension jsapiServiceExtension;

  @PostConstruct
  public void init() {
    // 初始化商户配置
    RSAConfig config =
        new RSAConfig.Builder()
            .merchantId(merchantId)
            // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
            .privateKeyFromPath(privateKeyPath)
            .merchantSerialNumber(merchantSerialNumber)
            .wechatPayCertificatesFromPath(wechatPayCertificatePath)
            .build();
    // 初始化服务
    jsapiServiceExtension =
        new JsapiServiceExtension.Builder()
            .config(config)
            .signType("RSA") // 不填默认为RSA
            .build();
  }
}
