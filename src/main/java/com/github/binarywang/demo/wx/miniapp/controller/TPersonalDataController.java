package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolType;
import com.github.binarywang.demo.wx.miniapp.entity.TPersonalData;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolTypeService;
import com.github.binarywang.demo.wx.miniapp.service.ITPersonalDataService;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户资料表 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/tPersonalData")
public class TPersonalDataController {
    @Autowired
    private ITPersonalDataService tPersonalDataService;
    /**
     * 获取个人资料
     * @return
     */
    @GetMapping("/getPersonalData/{userId}")
    public ResponseDTO<TPersonalData> getPersonalData(@PathVariable("userId") Integer userId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        TPersonalData tPersonalData=tPersonalDataService.getOne(qw);
        return ResponseDTO.success(tPersonalData);
    }
    /**
     * 保存个人资料
     * @return
     */
    @GetMapping("/savePersonalData")
    public ResponseDTO<TPersonalData> getAllSchoolType(@RequestBody TPersonalData data,@RequestHeader(value = "token") String token){
        try {
            Claims claims = JwtUtil.validateToken(token).getClaims();
            if (claims != null) {
                Integer userId = Integer.parseInt(claims.getId());
                QueryWrapper qw=new QueryWrapper();
                qw.eq("user_id",userId);
                TPersonalData tPersonalData=tPersonalDataService.getOne(qw);
                if(null == tPersonalData){
                    data.setSaveTimes(1);
                    tPersonalDataService.saveTPersonalData(data);
                }else{
                    if(tPersonalData.getSaveTimes() >=2){
                        return  ResponseDTO.error(100,"已超过修改上限");
                    }else{
                        data.setSaveTimes(tPersonalData.getSaveTimes()+1);
                        tPersonalDataService.update(data,new UpdateWrapper<TPersonalData>().eq("user_id",userId));
                    }
                }
            }
        }catch (Exception ex){
            return  ResponseDTO.error(500,ex.getMessage());
        }

        return ResponseDTO.success(data);
    }
}

