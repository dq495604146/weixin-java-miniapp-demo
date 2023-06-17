package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.City;
import com.github.binarywang.demo.wx.miniapp.entity.Province;
import com.github.binarywang.demo.wx.miniapp.service.ICityService;
import com.github.binarywang.demo.wx.miniapp.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-16
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    /**
     * 获取省份所有城市
     * @return
     */
    @GetMapping("/getCityByProvince/{provinceId}")
    public ResponseDTO<List<City>> getCityByProvince(@PathVariable("provinceId") String provinceId){
        QueryWrapper qw=new QueryWrapper();
        qw.orderByAsc("province_id",provinceId);
        List<City> list=cityService.list(qw);
        return ResponseDTO.success(list);

    }
}

