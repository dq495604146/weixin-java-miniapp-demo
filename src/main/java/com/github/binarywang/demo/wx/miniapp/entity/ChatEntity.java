package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_chat")
public class ChatEntity {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private Integer userId;
    private String content;
    private String replay;
    private Date createTime;
}
