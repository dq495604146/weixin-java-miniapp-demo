package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("xuanke")
public class XuankeEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String xuankeCode;
    private Integer gaokaoType;
    private String gaokaoTypeName;
}
