package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.TPersonalData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户资料表 服务类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
public interface ITPersonalDataService extends IService<TPersonalData> {
    public TPersonalData saveTPersonalData(TPersonalData tPersonalData);

    /**
     * 根据用户id获取考试个人资料信息
     * @param userId
     * @return
     */
    public TPersonalData getTPersonalDataByUserId(Integer userId);
}
