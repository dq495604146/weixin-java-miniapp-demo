package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 省份表
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "province_id", type = IdType.AUTO)
    private Integer provinceId;

    private String provinceName;


}
