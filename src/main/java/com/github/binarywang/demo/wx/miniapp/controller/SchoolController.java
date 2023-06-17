package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.School;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolType;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolService;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolTypeService;
import com.github.binarywang.demo.wx.miniapp.vo.SchoolQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private ISchoolService schoolService;
    /**
     * 获取学校详情
     * @return
     */
    @GetMapping("/getSchoolDetail/{schoolId}")
    public ResponseDTO<School> getSchoolDetail(@PathVariable("schoolId") String schoolId){
        School school=schoolService.getSchoolDetail(schoolId);
        return ResponseDTO.success(school);
    }


    /**
     * 获取学校列表
     * @return
     */
    @PostMapping("/querySchoolList")
    public ResponseDTO<List<School>> querySchoolList(@RequestBody SchoolQueryVO schoolQueryVO){
        List<School> schoolList=schoolService.querySchoolList(schoolQueryVO);
        return ResponseDTO.success(schoolList);
    }
}

