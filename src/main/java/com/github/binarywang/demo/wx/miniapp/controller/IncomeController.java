package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.dto.IncomeDTO;
import com.github.binarywang.demo.wx.miniapp.entity.IncomeEntity;
import com.github.binarywang.demo.wx.miniapp.entity.WxUserEntity;
import com.github.binarywang.demo.wx.miniapp.service.IncomeService;
import com.github.binarywang.demo.wx.miniapp.service.WxUserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/income")
public class IncomeController {
  @Resource IncomeService incomeService;

  @Resource WxUserService wxUserService;

  @GetMapping("/query")
  public IncomeDTO getIncomeInfo(@RequestParam("user_id") int userId) {
    IncomeDTO incomeDTO = new IncomeDTO().setUserId(userId);
    // 分享人数
    int shareNumber = incomeService.getShareNumber(userId);
    incomeDTO.setShareNumber(shareNumber);
    if (shareNumber == 0) {
      return incomeDTO;
    }
    // 总收益
    WxUserEntity wxUserEntity = wxUserService.queryById(userId);
    incomeDTO.setTotalMoney(wxUserEntity.getIncome());
    List<IncomeEntity> incomeEntities = incomeService.getLatestByUserId(userId, 20);
    List<Integer> userIds =
        incomeEntities.stream().map(IncomeEntity::getUserId).collect(Collectors.toList());
    List<WxUserEntity> wxUserEntities = wxUserService.queryByUserIds(userIds);
    List<String> phoneList =
        wxUserEntities.stream().map(WxUserEntity::getPhone).collect(Collectors.toList());
    return incomeDTO.setSharedUserPhoneList(phoneList);
  }
}
