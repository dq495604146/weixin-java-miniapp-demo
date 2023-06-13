package com.github.binarywang.demo.wx.miniapp.utils;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.Proxys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ChatGptUtils {

    @Value("${chatgpt.sk}")
    private String sk;

    public   String  send(String msg, List<Message> history){
//        Proxy proxy = Proxys.http("127.0.0.1", 1080);
        ChatGPT chatGPT = ChatGPT.builder()
            .apiKey(sk)
//            .proxy(proxy)
            .timeout(900)
            .apiHost("https://api.openai.com/") //反向代理地址
            .build()
            .init();

        Message system = Message.ofSystem("你现在是一位专业的高考志愿填报指导教师,你可以根据我的高考得分和排名以及每个学校历年录取的分数情况，然后再问我10个关于志愿填写的问题之后帮我推荐最符合我的十个学校并给出推荐理由及录取的难度,但问题要在我回复完一个后再问下一个，如果没有合适的大学适合我，需要用温柔的话鼓励我，我的高考得分450分，在北京市理科排名100名，现在你开始问我问题吧");
        Message message = Message.of(msg);
        List<Message> messages = new ArrayList<>();
        messages.add(system);
        messages.addAll(history);
        messages.add(message);
        ChatCompletion chatCompletion = ChatCompletion.builder()
            .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
            .messages(messages)
            .maxTokens(4000)
            .temperature(0.9)
            .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        return res.getContent();
    }


}
