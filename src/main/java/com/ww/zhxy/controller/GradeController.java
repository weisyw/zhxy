package com.ww.zhxy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.zhxy.pojo.Grade;
import com.ww.zhxy.service.GradeService;
import com.ww.zhxy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sms/gradeController")
@Slf4j
@Transactional
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 分页 查询 显示 年级
     * @param pageNum
     * @param pageSize
     * @param gradeName
     * @return
     */
    @GetMapping("/getGrades/{pageNum}/{pageSize}")
    public Result getGrades(@PathVariable Integer pageNum, @PathVariable Integer pageSize, String gradeName){
        log.info("当前页{}，总页数{}", pageNum, pageSize);
        // 分页
        Page<Grade> page = new Page<>(pageNum, pageSize);
        // 查询
        IPage<Grade> pageInfo = gradeService.getGradeByOpr(page, gradeName);
        return Result.ok(pageInfo);
    }

    /**
     * 添加 修改 年级
     * @param grade
     * @return
     */
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade grade) {
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    /**
     * 删除 年级
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteGrade")
    public Result deleteGradeById(@RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 班级管理显示年级下拉框
     * @return
     */
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }
}
