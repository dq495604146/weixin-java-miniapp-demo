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
public class SchoolSpecials implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long schoolId;

    private String specialName;

    private Long specialId;

    private String rankNum;

    private String rankPct;

    private String evalLevel;

    private String limitYear;

    private Long nationFeature;

    private String specialClassName;


}
