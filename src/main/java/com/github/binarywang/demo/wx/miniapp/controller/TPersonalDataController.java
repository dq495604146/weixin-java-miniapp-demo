package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolType;
import com.github.binarywang.demo.wx.miniapp.entity.TPersonalData;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolTypeService;
import com.github.binarywang.demo.wx.miniapp.service.ITPersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户资料表 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/tPersonalData")
public class TPersonalDataController {
    @Autowired
    private ITPersonalDataService tPersonalDataService;
    /**
     * 获取个人资料
     * @return
     */
    @GetMapping("/getPersonalData/{openid}")
    public ResponseDTO<TPersonalData> getPersonalData(@PathVariable("openid") String openid){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("open_id",openid);
        TPersonalData tPersonalData=tPersonalDataService.getOne(qw);
        return ResponseDTO.success(tPersonalData);
    }
    /**
     * 保存个人资料
     * @return
     */
    @GetMapping("/savePersonalData")
    public ResponseDTO<TPersonalData> getAllSchoolType(@RequestBody TPersonalData data){
        tPersonalDataService.saveTPersonalData(data);
        return ResponseDTO.success(data);
    }
}

