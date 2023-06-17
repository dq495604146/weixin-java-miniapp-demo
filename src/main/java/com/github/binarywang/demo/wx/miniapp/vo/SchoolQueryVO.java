package com.github.binarywang.demo.wx.miniapp.vo;

import lombok.Data;

import java.util.List;

/**
 * 学校列表查询vo
 */
@Data
public class SchoolQueryVO {
    /**
     * 省份
     */
    private String provinceId;

    /**
     * 城市
     */
    private String city;

    /**
     * 学校类型
     */
    private String schoolType;

    /**
     * 专业
     */
    private String specials;
}
