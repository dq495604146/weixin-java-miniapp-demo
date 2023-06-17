package com.github.binarywang.demo.wx.miniapp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 省市一起返回
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cityName;

    private Integer provinceId;


}
