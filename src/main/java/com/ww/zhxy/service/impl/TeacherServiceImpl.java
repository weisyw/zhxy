package com.ww.zhxy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.zhxy.mapper.TeacherMapper;
import com.ww.zhxy.pojo.LoginForm;
import com.ww.zhxy.pojo.Student;
import com.ww.zhxy.pojo.Teacher;
import com.ww.zhxy.service.TeacherService;
import com.ww.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("teaService")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Teacher login(LoginForm loginForm) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getName, loginForm.getUsername());
        queryWrapper.eq(Teacher::getPassword, MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public Teacher getTeacherById(int intValue) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getId, intValue);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<Teacher> getTeachersByOpr(Page<Teacher> pageParam, Teacher teacher) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(teacher.getName())) {
            queryWrapper.like(Teacher::getName, teacher.getName());
        }
        if (!StringUtils.isEmpty(teacher.getClazzName())) {
            queryWrapper.like(Teacher::getClazzName,teacher.getClazzName());
        }
        queryWrapper.orderByDesc(Teacher::getId);
        Page<Teacher> page1 = baseMapper.selectPage(pageParam, queryWrapper);
        return page1;
    }
}
