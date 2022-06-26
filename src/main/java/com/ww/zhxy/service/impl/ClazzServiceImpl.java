package com.ww.zhxy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.zhxy.mapper.ClazzMapper;
import com.ww.zhxy.pojo.Clazz;
import com.ww.zhxy.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Override
    public IPage<Clazz> getGradeByOpr(Page<Clazz> page, Clazz clazz) {
        LambdaQueryWrapper<Clazz> queryWrapper = new LambdaQueryWrapper<>();
        String gradeName = clazz.getGradeName();
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like(Clazz::getGradeName, gradeName);
        }
        String name = clazz.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(Clazz::getName, name);
        }
        queryWrapper.orderByDesc(Clazz::getId);
        Page<Clazz> page1 = baseMapper.selectPage(page, queryWrapper);
        return page1;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}
