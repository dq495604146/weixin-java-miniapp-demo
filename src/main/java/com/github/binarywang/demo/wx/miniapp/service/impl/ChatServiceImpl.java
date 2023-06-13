package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.dao.ChatDao;
import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;
import com.github.binarywang.demo.wx.miniapp.service.ChatService;
import com.plexpt.chatgpt.entity.chat.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("chatService")
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {
    @Override
    public List<ChatEntity> getChatByUserId(Integer userId) {
        return list(new QueryWrapper<ChatEntity>().eq("user_id",userId).orderByDesc("create_time"));
    }

    @Override
    public List<Message> getMessageByUserId(Integer userId) {
        List<ChatEntity> r = getChatByUserId(userId);
        List<Message> messages = new ArrayList<>();
        for (ChatEntity chat:r) {
            messages.add(Message.of(chat.getContent()));
            messages.add(Message.of(chat.getReplay()));
        }
        return messages;
    }

    @Override
    public void addChat(ChatEntity chatEntity) {
        save(chatEntity);
    }
}
