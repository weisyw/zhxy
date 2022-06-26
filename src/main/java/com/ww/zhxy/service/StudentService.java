package com.ww.zhxy.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.zhxy.pojo.LoginForm;
import com.ww.zhxy.pojo.Student;


public interface StudentService extends IService<Student> {


    Student login(LoginForm loginForm);

    Student getStudentById(int intValue);

    IPage<Student> getStudentByOpr(Page<Student> page, Student student);
}
