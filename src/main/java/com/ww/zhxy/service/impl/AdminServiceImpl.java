package com.ww.zhxy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.zhxy.mapper.AdminMapper;
import com.ww.zhxy.pojo.Admin;
import com.ww.zhxy.pojo.LoginForm;
import com.ww.zhxy.service.AdminService;
import com.ww.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin login(LoginForm loginForm) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName, loginForm.getUsername());
        queryWrapper.eq(Admin::getPassword, MD5.encrypt(loginForm.getPassword()));
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(int userId) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getId, userId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<Admin> getAdmins(Page<Admin> pageInfo, String adminName) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(adminName)){
            queryWrapper.like(Admin::getName,adminName);
        }
        queryWrapper.orderByDesc(Admin::getId);
        queryWrapper.orderByAsc(Admin::getName);
        Page page = baseMapper.selectPage(pageInfo, queryWrapper);
        return page;
    }
}
