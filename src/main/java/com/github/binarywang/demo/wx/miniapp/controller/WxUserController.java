package com.github.binarywang.demo.wx.miniapp.controller;

import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.github.binarywang.demo.wx.miniapp.constant.ErrorEnum;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.service.WxUserService;
import com.github.binarywang.demo.wx.miniapp.utils.PermissionManager;
import java.util.Map;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/wx/user")
public class WxUserController {

  @Resource WxUserService wxUserService;

  @GetMapping("/login")
  public Map<String, Object> login(String code, String phone) throws SystemException {
    if (StringUtils.isBlank(code)) {
      throw SystemException.buildSystemException(ErrorEnum.JsCodeIsEmpty);
    }
    try {
      return wxUserService.login(code, phone);
    } catch (WxErrorException e) {
      throw new SystemException(e.getError().getErrorCode(), e.getError().getErrorMsg());
    } finally {
      WxMaConfigHolder.remove();
    }
  }

  @GetMapping("/info")
  public WxUserEntity info(Integer id, String openid, String phone) {
    WxUserEntity wxUserEntity = null;
    if (id != null) {
      wxUserEntity = wxUserService.queryById(id);
    }
    if (Strings.isNotBlank(openid)) {
      wxUserEntity = wxUserService.queryByOpenId(openid);
    }
    if (Strings.isNotBlank(phone)) {
      wxUserEntity = wxUserService.queryByPhone(phone);
    }
    if (wxUserEntity != null) {
      PermissionManager permissionManager = new PermissionManager();
      wxUserEntity.setProductIds(
          permissionManager.productFlag2productIdList(wxUserEntity.getProductFlag()));
    }
    return wxUserEntity;
  }

  @PostMapping("/update")
  public boolean update(@RequestBody WxUserEntity wxUserEntity) {
    return wxUserService.updateWxUserEntity(wxUserEntity);
  }

  @PostMapping("updateByPhone")
  public boolean updateByPhone(@RequestBody WxUserEntity wxUserEntity) {
    return wxUserService.updateWxUserEntityByPhone(wxUserEntity);
  }
}
