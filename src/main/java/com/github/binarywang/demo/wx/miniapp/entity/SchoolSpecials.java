package com.github.binarywang.demo.wx.miniapp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学校专业信息
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchoolSpecials implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer schoolId;

    private Integer specialId;

    private Integer batchId;

    private Integer wenlikeId;

    private String specialExtra;

    private Integer rankNum;

    private Double rankPct;

    private String evalLevel;

    private String limitYear;

    private Integer nationFeature;


}
