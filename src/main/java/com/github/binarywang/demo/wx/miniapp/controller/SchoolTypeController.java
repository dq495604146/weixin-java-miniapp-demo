package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.Province;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolType;
import com.github.binarywang.demo.wx.miniapp.service.IProvinceService;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 学校类型 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/schoolType")
public class SchoolTypeController {

    @Autowired
    private ISchoolTypeService schoolTypeService;
    /**
     * 获取所有学校类型
     * @return
     */
    @GetMapping("/getAllSchoolType")
    public ResponseDTO<List<SchoolType>> getAllSchoolType(){
        QueryWrapper qw=new QueryWrapper();
        qw.orderByAsc("school_type_id");
        List<SchoolType> list=schoolTypeService.list(qw);
        return ResponseDTO.success(list);
    }
}

