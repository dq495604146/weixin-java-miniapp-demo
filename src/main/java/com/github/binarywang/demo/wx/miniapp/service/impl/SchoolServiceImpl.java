package com.github.binarywang.demo.wx.miniapp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.entity.School;
import com.github.binarywang.demo.wx.miniapp.dao.SchoolDao;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolSpecials;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolSpecialsService;
import com.github.binarywang.demo.wx.miniapp.vo.SchoolQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolDao, School> implements ISchoolService {

    @Autowired
    private ISchoolSpecialsService schoolSpecialsService;
    @Override
    public School getSchoolDetail(String schoolId) {
        School school=this.getById(schoolId);
        //TODO 组装学校详情
        return school;
    }

    @Override
    public List<School> querySchoolList(SchoolQueryVO schoolQueryVO) {

        QueryWrapper qw=new QueryWrapper();
        qw.eq("province_id",schoolQueryVO.getProvinceId());
        qw.eq(StrUtil.isNotEmpty(schoolQueryVO.getCity()),"city_name",schoolQueryVO.getCity());
        qw.eq(StrUtil.isNotEmpty(schoolQueryVO.getSchoolType()),"school_type_id",schoolQueryVO.getSchoolType());

        if(StrUtil.isNotEmpty(schoolQueryVO.getSpecials())){
            QueryWrapper specialsQw=new QueryWrapper();
            specialsQw.eq(StrUtil.isNotEmpty(schoolQueryVO.getSpecials()),"special_id",schoolQueryVO.getSpecials());
            List<Integer> schoolIdList= (List<Integer>) schoolSpecialsService.list(specialsQw).stream().map(obj->{
                SchoolSpecials schoolSpecials=(SchoolSpecials)obj;
                return schoolSpecials.getSchoolId();
            }).collect(Collectors.toList());

            qw.in(schoolIdList!=null ,"school_id",schoolIdList);
        }
        List<School> list=this.list(qw);
        return list;
    }
}
