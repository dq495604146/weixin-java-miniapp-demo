package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolSpecials;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolSpecialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  学校专业 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-18
 */
@RestController
@RequestMapping("/schoolSpecials")
public class SchoolSpecialsController {
    @Autowired
    private ISchoolSpecialsService schoolSpecialsService;
    /**
     * 获取学校开设的专业列表
     * @return
     */
    @GetMapping("/getSpecialsList/{schoolId}")
    public ResponseDTO<List<SchoolSpecials>> getSpecialsList(@PathVariable("schoolId") String schoolId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("school_id",schoolId);
        qw.orderByAsc("special_id");
        List<SchoolSpecials> list=schoolSpecialsService.list(qw);
        return ResponseDTO.success(list);
    }
}

