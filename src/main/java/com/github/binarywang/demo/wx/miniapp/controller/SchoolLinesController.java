package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolSpecials;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolLinesService;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolSpecialsService;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 录取分数线 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/schoolLines")
public class SchoolLinesController {
    @Autowired
    private ISchoolLinesService schoolLinesService;
    /**
     * 获取学校详情 分数线页面 数据
     * @return
     */
    @GetMapping("/getSpecialsList/{schoolId}")
    public ResponseDTO<HashMap<String,Object>> getSchoolDetailLinesData(@RequestHeader(value = "token") String token, @PathVariable("schoolId") String schoolId){
        Claims claims = JwtUtil.validateToken(token).getClaims();
        Integer userId = Integer.parseInt(claims.getId());
        HashMap<String,Object> list=schoolLinesService.getSchoolDetailLinesData(userId,schoolId);
        return ResponseDTO.success(list);
    }

}

