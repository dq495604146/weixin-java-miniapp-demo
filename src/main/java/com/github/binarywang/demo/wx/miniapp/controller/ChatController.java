package com.github.binarywang.demo.wx.miniapp.controller;

import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.github.binarywang.demo.wx.miniapp.dao.ChatDao;
import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;
import com.github.binarywang.demo.wx.miniapp.service.ChatService;
import com.github.binarywang.demo.wx.miniapp.utils.ChatGptUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;
    @GetMapping("/chat")
    public void getMedia() {

        List<ChatEntity> re= chatService.getChatByUserId(10);
        ChatGptUtils.send("11");
    }
}
