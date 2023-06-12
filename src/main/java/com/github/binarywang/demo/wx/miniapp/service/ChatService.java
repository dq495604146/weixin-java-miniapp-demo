package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;

import java.util.List;

public interface ChatService {

    List<ChatEntity> getChatByUserId(Integer userId);
}
