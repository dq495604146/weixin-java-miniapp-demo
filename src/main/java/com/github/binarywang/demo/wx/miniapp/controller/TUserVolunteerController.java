package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.TPersonalData;
import com.github.binarywang.demo.wx.miniapp.entity.TUserVolunteer;
import com.github.binarywang.demo.wx.miniapp.service.ITUserVolunteerService;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户志愿表 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-18
 */
@RestController
@RequestMapping("/tUserVolunteer")
public class TUserVolunteerController {

    @Autowired
    private ITUserVolunteerService tUserVolunteerService;
    /**
     * 添加志愿
     * @return
     */
    @PostMapping("/addVolunteer")
    public ResponseDTO<Boolean> addVolunteer(@RequestBody TUserVolunteer tUserVolunteer, @RequestHeader(value = "token") String token){
        boolean result=tUserVolunteerService.save(tUserVolunteer);
        return ResponseDTO.success(result);
    }

    /**
     * 删除志愿
     * @return
     */
    @DeleteMapping ("/deleteVolunteer/{volunteerId}")
    public ResponseDTO<Boolean> deleteVolunteer(@PathVariable("volunteerId") Integer volunteerId, @RequestHeader(value = "token") String token){
        Claims claims = JwtUtil.validateToken(token).getClaims();
        boolean result=tUserVolunteerService.removeById(volunteerId);
        return ResponseDTO.success(result);
    }

    /**
     * 获取个人志愿列表
     * @return
     */
    @GetMapping ("/listVolunteer")
    public ResponseDTO<List<HashMap<String,Object>>> listVolunteer(@RequestHeader(value = "token") String token){
        Claims claims = JwtUtil.validateToken(token).getClaims();
        Integer userId = Integer.parseInt(claims.getId());
        List<HashMap<String,Object>> list=tUserVolunteerService.queryVolunteerByUserId(userId);
        return ResponseDTO.success(list);
    }


}

