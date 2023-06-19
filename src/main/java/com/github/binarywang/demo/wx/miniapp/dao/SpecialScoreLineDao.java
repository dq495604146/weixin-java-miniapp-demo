package com.github.binarywang.demo.wx.miniapp.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.entity.SchoolSpecials;
import com.github.binarywang.demo.wx.miniapp.entity.SpecialScoreLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarywang.demo.wx.miniapp.service.ISchoolSpecialsService;
import com.github.binarywang.demo.wx.miniapp.vo.SpecialScoreLineVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 学校专业录取分数表 Mapper 接口
 * </p>
 *
 * @author dingqiang
 * @since 2023-06-14
 */
public interface SpecialScoreLineDao extends BaseMapper<SpecialScoreLine> {

    /**
     * 根据学校和身份获取学校专业分数线
     * @param schoolId
     * @param provinceId
     * @return
     */
    @Select("SELECT A.special_class_name as branchname,A.special_name as majorname," +
        "'' as describe,'' as requirement,B.year,B.score as minnum,B.rank as minrank," +
        "B.school_id as schoolId,B.special_id as specialId FROM  school_specials as A INNER JOIN " +
        "special_score_line as B ON A.school_id=B.school_id and A.special_id=B.special_id WHERE B.province_id=#{provinceId} and B.school_id=#{schoolId}")
    public List<SpecialScoreLineVO> querySchoolSpecialScoreLines(@Param("schoolId") Integer schoolId,@Param("provinceId") Integer provinceId);

}
