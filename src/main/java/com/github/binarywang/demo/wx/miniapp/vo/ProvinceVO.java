package com.github.binarywang.demo.wx.miniapp.vo;

import com.github.binarywang.demo.wx.miniapp.entity.City;
import lombok.Data;

import java.util.List;

@Data
public class ProvinceVO {
    private Integer provinceId;

    private String provinceName;

    private int hot;

    private int gaokaoType;

    private List<City> list;
}
