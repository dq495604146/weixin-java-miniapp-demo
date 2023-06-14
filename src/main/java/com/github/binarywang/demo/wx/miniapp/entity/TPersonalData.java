package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户资料表
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TPersonalData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openId;

    /**
     * 分数
     */
    private Integer scope;

    /**
     * 排名
     */
    private Integer ranking;

    /**
     * 省份
     */
    private Integer provinceId;

    /**
     * 科目：文科/理科
     */
    private String subject;


}
