package com.github.binarywang.demo.wx.miniapp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学校特色
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchoolFeature implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer schoolFeatureId;

    private String schoolFeatureName;


}