package com.github.binarywang.demo.wx.miniapp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学校专业录取分数表
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpecialScoreLine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer schoolId;

    private Integer specialId;

    private Integer year;

    private Double score;

    private Integer rank;


}
