package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;
import com.plexpt.chatgpt.entity.chat.Message;

import java.util.List;

public interface ChatService {

    List<ChatEntity> getChatByUserId(Integer userId);
    List<Message> getMessageByUserId(Integer userId);
    void  addChat(ChatEntity chatEntity);
}
