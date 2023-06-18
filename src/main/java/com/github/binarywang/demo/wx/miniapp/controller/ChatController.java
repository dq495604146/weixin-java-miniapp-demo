package com.github.binarywang.demo.wx.miniapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.entity.ChatEntity;
import com.github.binarywang.demo.wx.miniapp.entity.TPersonalData;
import com.github.binarywang.demo.wx.miniapp.result.R;
import com.github.binarywang.demo.wx.miniapp.service.ChatService;
import com.github.binarywang.demo.wx.miniapp.service.ITPersonalDataService;
import com.github.binarywang.demo.wx.miniapp.utils.ChatGptUtils;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import com.plexpt.chatgpt.entity.chat.Message;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;

    @Resource
    private  ChatGptUtils chatGptUtils;

    @Autowired
    private ITPersonalDataService tPersonalDataService;
    @PostMapping("/chat")
    public R chatBot(@RequestBody String message, @RequestHeader(value = "token") String token) {
        try {

            Claims claims = JwtUtil.validateToken(token).getClaims();
            if (claims != null) {
                Integer userId = Integer.parseInt(claims.getId());
                QueryWrapper qw=new QueryWrapper();
                qw.eq("user_id",userId);
                TPersonalData tPersonalData=tPersonalDataService.getOne(qw);

//                System.out.println("openid=: " + claims.getId());
                List<Message> history= chatService.getMessageByUserId( userId);
                String reply = chatGptUtils.send(message,history,tPersonalData);
                ChatEntity chatEntity = new ChatEntity();
                chatEntity.setContent(message);
                chatEntity.setReplay(reply);
                chatEntity.setUserId(userId);
                chatService.addChat(chatEntity);
                return  R.ok(reply);
            }else{
                return R.error(401,"鉴权失败");
            }
        }catch (Exception exception){
            return R.error(exception.getMessage());
        }

    }

}
