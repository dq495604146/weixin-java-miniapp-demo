package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.dao.ChatDao;
import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;
import com.github.binarywang.demo.wx.miniapp.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("chatService")
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {
    @Override
    public List<ChatEntity> getChatByUserId(Integer userId) {
        Map<String,Object> params = new HashMap<>();
        params.put("user_id",userId);
        return listByMap(params);
    }
}
