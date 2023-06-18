package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.demo.wx.miniapp.vo.ProvinceVO;

import java.util.List;

/**
 * <p>
 * 省份表 服务类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
public interface IProvinceService extends IService<Province> {

    /**
     * 获取所有省市信息
     * @return
     */
    public List<ProvinceVO> getAllProvinceIncludeCity();

}
