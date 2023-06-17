package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolFeature;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 学校特色 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/schoolFeature")
public class SchoolFeatureController {
    @Autowired
    private ISchoolFeatureService schoolFeatureService;
    /**
     * 获取特色 （985，211，双一流,其它）
     * @return
     */
    @GetMapping("/getAllSchoolFeature")
    public ResponseDTO<List<SchoolFeature>> getAllSchoolFeature(){
        QueryWrapper qw=new QueryWrapper();
        qw.orderByAsc("school_feature_id");
        List<SchoolFeature> list=schoolFeatureService.list(qw);
        return ResponseDTO.success(list);
    }
}

