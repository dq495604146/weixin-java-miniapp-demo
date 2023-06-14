package com.github.binarywang.demo.wx.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 录取分数线
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchoolLines implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer schoolId;

    private Integer year;

    private Double score;

    private Integer rank;

    private Integer batchId;

    private Integer zslxId;

    private Integer wenlikeId;


}
