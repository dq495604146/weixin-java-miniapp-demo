package com.github.binarywang.demo.wx.miniapp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 专业相关得学科表
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String subject;


}
