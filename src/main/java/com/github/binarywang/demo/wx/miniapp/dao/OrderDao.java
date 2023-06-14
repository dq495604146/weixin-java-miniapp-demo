package com.github.binarywang.demo.wx.miniapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarywang.demo.wx.miniapp.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {}
