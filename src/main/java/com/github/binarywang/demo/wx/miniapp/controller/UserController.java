package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

  @Resource UserService userService;

  @GetMapping("/login")
  public Map<String, String> login(WxUserEntity wxUser) throws SystemException {
    String token;
    token = userService.login(wxUser);
    Map<String, String> map = new HashMap<>();
    map.put("token", token);
    return map;
  }
}
