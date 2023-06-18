package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.SchoolLines;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 录取分数线 服务类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
public interface ISchoolLinesService extends IService<SchoolLines> {

    /**
     * 获取学校详情 分数线页面 数据
     * @param userid 用户id
     * @param schoolId 学校id
     * @return
     */
    HashMap<String, Object> getSchoolDetailLinesData(Integer userid, String schoolId);
}
