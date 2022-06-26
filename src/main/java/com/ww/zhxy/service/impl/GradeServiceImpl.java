package com.ww.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.zhxy.mapper.GradeMapper;
import com.ww.zhxy.pojo.Grade;
import com.ww.zhxy.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
        LambdaQueryWrapper<Grade> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like(Grade::getName ,gradeName);
        }
        queryWrapper.orderByAsc(Grade::getId);
        Page<Grade> page1 = baseMapper.selectPage(page, queryWrapper);
        return page1;
    }

    @Override
    public List<Grade> getGrades() {
        return baseMapper.selectList(null);
    }
}
