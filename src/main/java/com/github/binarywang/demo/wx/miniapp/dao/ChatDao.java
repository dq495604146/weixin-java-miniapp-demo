package com.github.binarywang.demo.wx.miniapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatDao  extends BaseMapper<ChatEntity> {
}
