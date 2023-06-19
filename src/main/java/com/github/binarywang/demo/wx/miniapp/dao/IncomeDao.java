package com.github.binarywang.demo.wx.miniapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarywang.demo.wx.miniapp.entity.IncomeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IncomeDao extends BaseMapper<IncomeEntity> {}
