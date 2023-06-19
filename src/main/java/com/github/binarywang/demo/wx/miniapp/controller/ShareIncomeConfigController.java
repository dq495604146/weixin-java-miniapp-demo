package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.entity.ShareIncomeConfigEntity;
import com.github.binarywang.demo.wx.miniapp.service.ShareIncomeConfigService;
import java.util.List;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/shareIncome")
public class ShareIncomeConfigController {

  @Resource ShareIncomeConfigService shareIncomeConfigService;

  @GetMapping("/config")
  public List<ShareIncomeConfigEntity> getConfig() {
    return shareIncomeConfigService.list();
  }
}
