package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.demo.wx.miniapp.vo.SubjectVO;

import java.util.List;

/**
 * <p>
 * 专业相关得学科表 服务类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
public interface ISubjectService extends IService<Subject> {
    /**
     * 专业树
     * @return
     */
    public List<SubjectVO> getSubjectVOTreeList();
}
