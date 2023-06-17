package com.github.binarywang.demo.wx.miniapp.controller;


import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.School;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolService;
import com.github.binarywang.demo.wx.miniapp.service.ISubjectService;
import com.github.binarywang.demo.wx.miniapp.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 专业相关得学科表 前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;
    /**
     * 获取学科树列表
     * @return
     */
    @GetMapping("/getSubjectTreeList")
    public ResponseDTO<List<SubjectVO>> getSubjectTreeList(){
        List<SubjectVO> list=subjectService.getSubjectVOTreeList();
        return ResponseDTO.success(list);
    }
}

