package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import java.util.Map;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WxUserService {
  Map<String, Object> login(String code, String phone) throws SystemException, WxErrorException;

  WxUserEntity queryById(int id);

  WxUserEntity queryByOpenId(String openId);

  WxUserEntity queryByPhone(String phone);

  boolean updateWxUserEntity(WxUserEntity wxUserEntity);

  boolean updateWxUserEntityByPhone(WxUserEntity wxUserEntity);
}
