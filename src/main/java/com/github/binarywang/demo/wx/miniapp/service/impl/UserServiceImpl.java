package com.github.binarywang.demo.wx.miniapp.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.constant.WxUserConstant;
import com.github.binarywang.demo.wx.miniapp.dao.UserDao;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.service.UserService;
import com.github.binarywang.demo.wx.miniapp.utils.HttpClient;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, WxUserEntity> implements UserService {
  @Value("${wx.miniapp.jscode2sessionUrl}")
  private String jsCode2SessionUrl;

  @Resource WxMaService wxMaService;

  @Resource private HttpClient httpClient;

  public String login(WxUserEntity wxUser) throws SystemException {
    WxMaConfig wxMaConfig = wxMaService.getWxMaConfig();
    String request =
        jsCode2SessionUrl
            + "?appid="
            + wxMaConfig.getAppid()
            + "&secret="
            + wxMaConfig.getSecret()
            + "&js_code="
            + wxUser.getCode()
            + "&grant_type=authorization_code";
    String response = httpClient.sendHttpsGet(request);
    JSONObject result = JSON.parseObject(response);
    int errCode = result.getIntValue("errcode");
    if (errCode != 0) {
      throw new SystemException(errCode, result.getString("errmsg"));
    }
    String openid = result.getString("openid");
    WxUserEntity wxUserEntity = getOne(new QueryWrapper<>(new WxUserEntity().setOpenid(openid)));
    if (null == wxUserEntity) {
      wxUser.setOpenid(openid);
      wxUser.setRegisterDate(new Date());
      wxUser.setLastLoginDate(new Date());
      save(wxUser);
    } else {
      wxUserEntity.setNickName(wxUser.getNickName());
      wxUserEntity.setAvatarUrl(wxUser.getAvatarUrl());
      wxUserEntity.setLastLoginDate(new Date());
      updateById(wxUserEntity);
    }
    return JwtUtil.generateToken(openid, wxUser.getNickName(), WxUserConstant.Jwt_Ttl);
  }

  @Override
  public WxUserEntity queryById(int id) {
    return getOne(new QueryWrapper<>(new WxUserEntity().setId(id)));
  }

  @Override
  public void updateWxUserEntity(WxUserEntity wxUserEntity) {
    updateById(wxUserEntity);
  }
}
