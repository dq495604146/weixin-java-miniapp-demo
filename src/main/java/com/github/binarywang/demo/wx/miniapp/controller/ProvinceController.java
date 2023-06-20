package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.Province;
import com.github.binarywang.demo.wx.miniapp.service.IProvinceService;
import com.github.binarywang.demo.wx.miniapp.vo.ProvinceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 省份表 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;

    /**
     * 获取所有省份直辖市
     * @return
     */
    @GetMapping("/getAllProvince")
    public ResponseDTO<List<Province>> getAllProvince(){
        QueryWrapper qw=new QueryWrapper();
        qw.orderByAsc("province_id");
        List<Province> list=provinceService.list(qw);
        return ResponseDTO.success(list);

    }

    /**
     * 获取所有省份直辖市（包含城市信息）
     * @return
     */
    @GetMapping("/getAllProvinceIncludeCity")
    public ResponseDTO<List<ProvinceVO>> getAllProvinceIncludeCity(){
        return ResponseDTO.success(provinceService.getAllProvinceIncludeCity());
    }
}

