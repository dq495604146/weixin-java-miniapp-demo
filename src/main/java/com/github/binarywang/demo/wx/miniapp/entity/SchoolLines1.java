package com.github.binarywang.demo.wx.miniapp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchoolLines1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long provinceId;

    private Long xuankeId;

    private String courses;

    private Long batchId;

    private Long wenlikeId;

    private Double coursesId;

    private String wenlike;

    private Long zslxId;

    private String specialGroup;

    private Double coursesEncoding;

    private String batchName;

    private Long schoolId;

    private String school;

    private String zslx;

    private String lines;


}
