package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.demo.wx.miniapp.vo.SchoolQueryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
public interface ISchoolService extends IService<School> {

    /**
     * 根据学校id获取学校详情
     * @param schoolId
     * @return
     */
    School getSchoolDetail(String schoolId);

    /**
     * 根据参数查询学校列表
     * @param schoolQueryVO
     * @return
     */
    List<School> querySchoolList(SchoolQueryVO schoolQueryVO);
}
