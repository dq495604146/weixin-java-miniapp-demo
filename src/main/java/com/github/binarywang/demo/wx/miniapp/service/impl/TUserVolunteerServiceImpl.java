package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.entity.School;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolSpecials;
import com.github.binarywang.demo.wx.miniapp.entity.TUserVolunteer;
import com.github.binarywang.demo.wx.miniapp.dao.TUserVolunteerDao;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolService;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolSpecialsService;
import com.github.binarywang.demo.wx.miniapp.service.ITUserVolunteerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户志愿表 服务实现类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-18
 */
@Service
public class TUserVolunteerServiceImpl extends ServiceImpl<TUserVolunteerDao, TUserVolunteer> implements ITUserVolunteerService {

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private ISchoolSpecialsService schoolSpecialsService;
    @Override
    public List<HashMap<String,Object>> queryVolunteerByUserId(Integer userId) {
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        List<TUserVolunteer>  list=this.list(qw);
        HashMap<Integer,List<Integer>> schoolGroupMap=new HashMap<>();
        list.stream().forEach(tUserVolunteer -> {
            if(schoolGroupMap.containsKey(tUserVolunteer.getSchoolId())){
                schoolGroupMap.get(tUserVolunteer.getSchoolId()).add(tUserVolunteer.getSpecialId());
            }else{
                List<Integer> temp=new ArrayList<>();
                temp.add(tUserVolunteer.getSpecialId());
                schoolGroupMap.put(tUserVolunteer.getSchoolId(),temp);
            }

        });
        List<HashMap<String,Object>> result=new ArrayList<>();

        //按学校分组
        schoolGroupMap.entrySet().stream().forEach(entry->{
            HashMap<String,Object> map=new HashMap<>();
            Integer schoolId=entry.getKey();
            List<Integer> specialsListId=entry.getValue();
            School school=schoolService.getSchoolDetail(""+schoolId);
            map.put("schoolDetail",school);

            QueryWrapper specialsQw=new QueryWrapper();
            specialsQw.eq("school_id",schoolId);
            specialsQw.in("specials_id",specialsListId);

            List<SchoolSpecials> schoolSpecialsList=schoolSpecialsService.list(specialsQw);
            map.put("schoolSpecialsList",schoolSpecialsList);

            result.add(map);
        });

        return result;
    }
}
