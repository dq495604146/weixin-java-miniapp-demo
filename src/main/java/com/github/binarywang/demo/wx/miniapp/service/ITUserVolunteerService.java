package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.TUserVolunteer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户志愿表 服务类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-18
 */
public interface ITUserVolunteerService extends IService<TUserVolunteer> {

    /**
     * 查询个人志愿列表
     * @param userId
     * @return
     */
    List<HashMap<String,Object>> queryVolunteerByUserId(Integer userId);
}
