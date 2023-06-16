package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.dao.CityDao;
import com.github.binarywang.demo.wx.miniapp.entity.City;
import com.github.binarywang.demo.wx.miniapp.entity.Province;
import com.github.binarywang.demo.wx.miniapp.dao.ProvinceDao;
import com.github.binarywang.demo.wx.miniapp.service.IProvinceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.vo.ProvinceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 省份表 服务实现类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceDao, Province> implements IProvinceService {

    @Autowired
    private CityDao cityDao;
    @Override
    public List<ProvinceVO> getAllProvinceIncludeCity() {
        List<ProvinceVO> result=new ArrayList<>();
        List<Province> list=this.list();
        list.stream().forEach(province -> {
            ProvinceVO provinceVO=new ProvinceVO();
            provinceVO.setProvinceId(province.getProvinceId());
            provinceVO.setProvinceName(province.getProvinceName());
            provinceVO.setHot(province.getHot());
            HashMap<String,Object> map=new HashMap<>();
            map.put("province_id",province.getProvinceId());
            List<City> cities=cityDao.selectByMap(map);
            provinceVO.setList(cities);
            result.add(provinceVO);
        });
        return result;
    }
}
