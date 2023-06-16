package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.entity.TPersonalData;
import com.github.binarywang.demo.wx.miniapp.dao.TPersonalDataDao;
import com.github.binarywang.demo.wx.miniapp.service.ITPersonalDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户资料表 服务实现类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Service
public class TPersonalDataServiceImpl extends ServiceImpl<TPersonalDataDao, TPersonalData> implements ITPersonalDataService {

    @Transactional
    @Override
    public TPersonalData saveTPersonalData(TPersonalData tPersonalData) {
        QueryWrapper qw=new QueryWrapper();
        qw.eq("open_id",tPersonalData.getOpenId());
        TPersonalData old=this.getOne(qw);
        if(old==null){
            this.save(tPersonalData);
        }else{
            tPersonalData.setId(old.getId());
            this.updateById(old);
        }
        return tPersonalData;
    }
}
