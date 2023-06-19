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
 * @since 2023-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpecialScoreLine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer provinceId;

    private Integer schoolId;

    private Integer date;

    private String courses;

    private String batchName;

    private Integer batchId;

    private Integer specialId;

    private Integer coursesId;

    private Integer xuekeId;

    private String specialName;

    private String lines;


}
