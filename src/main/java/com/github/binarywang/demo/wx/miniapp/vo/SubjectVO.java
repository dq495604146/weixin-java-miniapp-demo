package com.github.binarywang.demo.wx.miniapp.vo;

import lombok.Data;

import java.util.List;

/**
 * 专业学科VO
 */
@Data
public class SubjectVO {
    private Integer id;

    private String subject;

    List<SpecialsVO> specialsVOList;
}
