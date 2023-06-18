package com.github.binarywang.demo.wx.miniapp.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.constant.WxUserConstant;
import com.github.binarywang.demo.wx.miniapp.dao.UserDao;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.service.WxUserService;
import com.github.binarywang.demo.wx.miniapp.utils.CommonUtil;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WxUserServiceImpl extends ServiceImpl<UserDao, WxUserEntity> implements WxUserService {
  @Resource WxMaService wxMaService;

  public Map<String, Object> login(String code, String phone) throws WxErrorException {
    WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
    String sessionKey = session.getSessionKey();
    String openid = session.getOpenid();
    String unionid = session.getUnionid();
    WxUserEntity wxUserEntity = queryByOpenId(openid);
    if (wxUserEntity == null) {
      wxUserEntity = new WxUserEntity();
      wxUserEntity.setOpenId(openid);
      wxUserEntity.setUnionId(unionid);
      wxUserEntity.setPhone(phone);
      wxUserEntity.setLastLoginDate(new Date());
      wxUserEntity.setRegisterDate(new Date());
      wxUserEntity.setSessionKey(sessionKey);
      save(wxUserEntity);
    } else {
      wxUserEntity.setSessionKey(sessionKey);
      wxUserEntity.setLastLoginDate(new Date());
      if (Strings.isNotBlank(phone)) {
        wxUserEntity.setPhone(phone);
      }
      updateById(wxUserEntity);
    }
    Map<String, Object> resultMap = CommonUtil.objectToMapWithCustomDateFormat(wxUserEntity);
    String token = JwtUtil.generateToken(openid, phone, WxUserConstant.Jwt_Ttl);
    resultMap.put("token", token);
    return resultMap;
  }

  @Override
  public WxUserEntity queryById(int id) {
    return getById(id);
  }

  @Override
  public WxUserEntity queryByOpenId(String openid) {
    return getOne(new QueryWrapper<>(new WxUserEntity().setOpenId(openid)));
  }

  @Override
  public WxUserEntity queryByPhone(String phone) {
    return getOne(new QueryWrapper<>(new WxUserEntity().setPhone(phone)));
  }

  @Override
  public boolean updateWxUserEntity(WxUserEntity wxUserEntity) {
    log.info("{}", wxUserEntity);
    return updateById(wxUserEntity);
  }

  @Override
  public boolean updateWxUserEntityByPhone(WxUserEntity wxUserEntity) {
    String phone = wxUserEntity.getPhone();
    if (Strings.isEmpty(phone)) {
      return true;
    }
    return update(
        wxUserEntity, new QueryWrapper<>(new WxUserEntity().setPhone(wxUserEntity.getPhone())));
  }
}
