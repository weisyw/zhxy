package com.ww.zhxy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.zhxy.mapper.StudentMapper;
import com.ww.zhxy.pojo.Admin;
import com.ww.zhxy.pojo.LoginForm;
import com.ww.zhxy.pojo.Student;
import com.ww.zhxy.service.StudentService;
import com.ww.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**

 */
@Service("stuService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public Student login(LoginForm loginForm) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getName, loginForm.getUsername());
        queryWrapper.eq(Student::getPassword, MD5.encrypt(loginForm.getPassword()));
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public Student getStudentById(int intValue) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getId, intValue);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<Student> getStudentByOpr(Page<Student> page, Student student) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(student.getName())) {
            queryWrapper.like(Student::getName, student.getName());
        }
        if (!StringUtils.isEmpty(student.getClazzName())) {
            queryWrapper.like(Student::getClazzName,student.getClazzName());
        }
        queryWrapper.orderByDesc(Student::getId);
        Page<Student> page1 = baseMapper.selectPage(page, queryWrapper);
        return page1;
    }
}
