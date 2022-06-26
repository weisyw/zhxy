package com.ww.zhxy.service;

import com.ww.zhxy.pojo.Admin;
import com.ww.zhxy.pojo.LoginForm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.zhxy.pojo.Admin;

public interface AdminService extends IService<Admin> {


    Admin login(LoginForm loginForm);

    Admin getAdminById(int userId);

    IPage<Admin> getAdmins(Page<Admin> pageInfo, String adminName);
}
