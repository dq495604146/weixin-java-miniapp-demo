package com.github.binarywang.demo.wx.miniapp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolBatch;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  学校批次前端控制器
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/schoolBatch")
public class SchoolBatchController {
    @Autowired
    private ISchoolBatchService schoolBatchService;
    /**
     * 获取所有批次
     * @return
     */
    @GetMapping("/getAllSchoolBatch")
    public ResponseDTO<List<SchoolBatch>> getAllSchoolBatch(){
        QueryWrapper qw=new QueryWrapper();
        qw.orderByAsc("batch_id");
        List<SchoolBatch> list=schoolBatchService.list(qw);
        return ResponseDTO.success(list);
    }
}

