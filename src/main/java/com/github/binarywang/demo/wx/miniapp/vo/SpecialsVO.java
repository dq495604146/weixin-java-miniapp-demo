package com.github.binarywang.demo.wx.miniapp.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 2，3级专业学科VO专业表
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpecialsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String specialClassName;

    /**
     * 专业分类id
     */
    private Integer specialClassId;

    private String specialName;

    private String specialId;

    private String specialCode;

    private String limitYear;

    /**
     * 学科id
     */
    private Integer subjectId;

    private String isWhat;

    private String doWhat;

    private String degree;

    private String direction;

    private String job;

    /**
     * 子学科专业
     */
    List<SpecialsVO> specialsVOList;
}
