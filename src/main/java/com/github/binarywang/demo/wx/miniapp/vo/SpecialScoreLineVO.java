package com.github.binarywang.demo.wx.miniapp.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class SpecialScoreLineVO {

    private static final long serialVersionUID = 1L;

    private Integer schoolId;

    private Integer specialId;

    private String branchname;

    private String majorname;

    private String describe;

    private String requirement;


    private Integer year;

    private Integer minnum;

    private Integer minrank;


}
