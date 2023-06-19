package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dao.SpecialScoreLineDao;
import com.github.binarywang.demo.wx.miniapp.entity.*;
import com.github.binarywang.demo.wx.miniapp.dao.SchoolLinesDao;
import com.github.binarywang.demo.wx.miniapp.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.vo.SpecialScoreLineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 录取分数线 服务实现类
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
@Service
public class SchoolLinesServiceImpl extends ServiceImpl<SchoolLinesDao, SchoolLines> implements ISchoolLinesService {

    @Autowired
    private ITPersonalDataService tPersonalDataService;

    @Autowired
    private ISchoolLines1Service schoolLines1Service;

    @Autowired
    private ISchoolLines2Service schoolLines2Service;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ISpecialScoreLineService specialScoreLineService;

    @Autowired
    private SpecialScoreLineDao specialScoreLineDao;
    @Override
    public HashMap<String, Object> getSchoolDetailLinesData(Integer userid, Integer schoolId) {

//        "schoolscores": [
//        {
//            "title":"普通类一段",
//            "describe":"招生类型 普通类",
//            "status":0,
//            "yearscores": [{
//            "year":"2022",
//                "minnum": "656",
//                "minrank": "720"
//        },{
//            "year":"2021",
//                "minnum": "655",
//                "minrank": "721"
//        },{
//            "year":"2020",
//                "minnum": "657",
//                "minrank": "722"
//        }]
//        }
//				],
//        "majorscores":[{
//            "branchname": "经济学",
//                "list": [{
//                "majorname": "临床医学（本硕博连读8年）",
//                    "describe": "普通类一段",
//                    "requirement": "物理必选",
//                    "year":"2022",
//                    "minnum": "657",
//                    "minrank": "722"
//            }, {
//                "majorname": "临床医学（本硕博连读8年）",
//                    "describe": "普通类一段",
//                    "requirement": "物理、化学（2科必选）",
//                    "year":"2022",
//                    "minnum": "657",
//                    "minrank": "722"
//            }, {
//                "majorname": "临床医学（本硕博连读8年）",
//                    "describe": "普通类一段",
//                    "requirement": "物理、化学（2科必选）",
//                    "year":"2021",
//                    "minnum": "657",
//                    "minrank": "722"
//            }]
//        },
//        {
//            "branchname": "哲学",
//            "list": [{
//            "majorname": "临床医学（本硕博连读8年）",
//                "describe": "普通类一段",
//                "requirement": "物理必选",
//                "year":"2022",
//                "minnum": "657",
//                "minrank": "722"
//        }, {
//            "majorname": "临床医学（本硕博连读8年）",
//                "describe": "普通类一段",
//                "requirement": "物理、化学（2科必选）",
//                "year":"2022",
//                "minnum": "657",
//                "minrank": "722"
//        }]
//        }
//				]
//    }
        //返回数据
        HashMap<String,Object> resultMap=new HashMap<>();

        TPersonalData tPersonalData=tPersonalDataService.getTPersonalDataByUserId(userid);
        int provinceId=tPersonalData.getProvinceId();
        int xuankeId=tPersonalData.getXuankeId();

        Province province=provinceService.getById(provinceId);

        QueryWrapper qw=new QueryWrapper();
        qw.eq("school_id",schoolId);
        qw.eq("province_id",provinceId);
        qw.eq("xuanke_id",xuankeId);

        if(0 == province.getGaokaoType()){//文理科
            List<SchoolLines2> list2=schoolLines2Service.list(qw);
            List<HashMap<String,Object>>  temp2= list2.stream().map(schoolLines2 -> {
                HashMap<String,Object> map=new HashMap<>();
                map.put("title",schoolLines2.getBatchName());
                map.put("describe",schoolLines2.getZslx());
                map.put("status",0);
                map.put("yearscores",schoolLines2.getLines());
                return map;
            }).collect(Collectors.toList());

            resultMap.put("schoolscores",temp2);

        }else{//新高考
            List<SchoolLines1> list1=schoolLines1Service.list(qw);
            List<HashMap<String,Object>>  temp1= list1.stream().map(schoolLines1 -> {
                HashMap<String,Object> map=new HashMap<>();
                map.put("title",schoolLines1.getBatchName());
                map.put("describe",schoolLines1.getZslx());
                map.put("status",0);
                map.put("yearscores",schoolLines1.getLines());
                return map;
            }).collect(Collectors.toList());
            resultMap.put("schoolscores",temp1);
        }
        //学校专业分数线
        HashMap<String,List<SpecialScoreLineVO>> majorscores=new HashMap<>();
        List<SpecialScoreLineVO>  specialScoreLineList=specialScoreLineDao.querySchoolSpecialScoreLines(schoolId,provinceId);

        specialScoreLineList.stream().forEach(specialScoreLineVO -> {
            if(majorscores.containsKey(specialScoreLineVO.getBranchname())){
                majorscores.get(specialScoreLineVO.getBranchname()).add(specialScoreLineVO);
            }else{
                List<SpecialScoreLineVO> temp=new ArrayList<>();
                temp.add(specialScoreLineVO);
                majorscores.put(specialScoreLineVO.getBranchname(),temp);
            }
        });
        List<HashMap<String,Object>> majorscoresResult=majorscores.entrySet().stream().map(entry->{
            HashMap<String,Object> map=new HashMap<>();
            map.put("branchname",entry.getKey());
            map.put("list",entry.getValue());
            return map;
        }).collect(Collectors.toList());
        resultMap.put("majorscores",majorscoresResult);
        return resultMap;
    }
}
