package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户志愿表
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUserVolunteer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 专业id
     */
    private Integer specialId;

    /**
     * 学校id
     */
    private Integer schoolId;


}
