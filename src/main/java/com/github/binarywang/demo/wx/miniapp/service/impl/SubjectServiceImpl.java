package com.github.binarywang.demo.wx.miniapp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.entity.Specials;
import com.github.binarywang.demo.wx.miniapp.entity.Subject;
import com.github.binarywang.demo.wx.miniapp.dao.SubjectDao;
import com.github.binarywang.demo.wx.miniapp.service.ISpecialsService;
import com.github.binarywang.demo.wx.miniapp.service.ISubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.vo.SpecialsVO;
import com.github.binarywang.demo.wx.miniapp.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 专业相关得学科表 服务实现类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, Subject> implements ISubjectService {

    @Autowired
    private ISpecialsService specialsService;
    @Override
    public List<SubjectVO> getSubjectVOTreeList() {
        //二级分类
        QueryWrapper qw2=new QueryWrapper();
        qw2.isNull("special_id");
        qw2.orderByAsc("special_class_id");
        List<Specials> secondList=specialsService.list(qw2);

        //三级分类
        QueryWrapper qw3=new QueryWrapper();
        qw3.isNotNull("special_id");
        qw3.orderByAsc("special_id");
        List<Specials> threeList=specialsService.list(qw3);

        //一级分类
        QueryWrapper qw1=new QueryWrapper();
        qw1.orderByAsc("id");
        List<Subject>  list=this.list(qw1);

        List<SubjectVO> resultList=new ArrayList<>();

        list.forEach(subject->{
            SubjectVO subjectVO=new SubjectVO();
            subjectVO.setId(subject.getId());
            subjectVO.setSubject(subject.getSubject());
            List<SpecialsVO> temp=new ArrayList<>();

            secondList.stream().filter(specials2 ->specials2.getSubjectId().equals(subject.getId()))
                .forEach(specials2->{
                    SpecialsVO specialsVO2=new SpecialsVO();
                    BeanUtil.copyProperties(specials2,specialsVO2);

                    List<SpecialsVO> specialsVO2List=new ArrayList<>();
                    threeList.stream().filter(specials3 ->specials3.getSubjectId().equals(subject.getId())
                        && specials3.getSpecialClassId().equals(specials2.getSpecialClassId())).forEach(specials3->{
                        SpecialsVO specialsVO3=new SpecialsVO();
                        BeanUtil.copyProperties(specials3,specialsVO3);
                        specialsVO2List.add(specialsVO3);
                    });
                    specialsVO2.setSpecialsVOList(specialsVO2List);
                    temp.add(specialsVO2);
            });
            subjectVO.setSpecialsVOList(temp);

            resultList.add(subjectVO);
        });

        return resultList;
    }
}
