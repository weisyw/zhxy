package com.ww.zhxy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.zhxy.pojo.Clazz;
import com.ww.zhxy.service.ClazzService;
import com.ww.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sms/clazzController")
@Transactional
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 班级管理 分页 查询
     * @param pageNum
     * @param pageSize
     * @param clazz
     * @return
     */
    @GetMapping("/getClazzsByOpr/{pageNum}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable Integer pageNum, @PathVariable Integer pageSize, Clazz clazz){
        Page<Clazz> page = new Page<>(pageNum, pageSize);
        IPage<Clazz> clazzIPage = clazzService.getGradeByOpr(page, clazz);
        return Result.ok(clazzIPage);
    }

    /**
     * 班级管理 修改和添加
     * @param clazz
     * @return
     */
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteClazz")
    public Result deleteClazzByIds( @RequestBody List<Integer> ids) {
        clazzService.removeByIds(ids);
        return Result.ok();
    }


    /**
     * 查询所有班级
     * @return
     */
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzList = clazzService.getClazzs();
        return Result.ok(clazzList);
    }

}
