package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer schoolId;

    private String name;

    private Integer provinceId;

    private String provinceName;

    private String typeName;

    private Integer f211;

    private Integer numMaster;

    private Integer usRank;

    private String content;

    private String schoolSite;

    private String featuresOut;

    private String cityName;

    private String schoolNatureName;

    private String logo;

    private Integer ruankeRank;

    private Integer qsWorld;

    private Integer dualClassName;

    private String address;

    private Integer xyhRank;

    private Integer wslRank;

    private Integer numDoctor;

    private String site;

    private String phone;

    private Integer schoolTypeId;

    private Integer f985;

    private String addtion;

    private String featuresInner;

}
