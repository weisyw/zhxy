package com.ww.zhxy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.zhxy.pojo.Student;
import com.ww.zhxy.service.StudentService;
import com.ww.zhxy.utils.MD5;
import com.ww.zhxy.utils.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sms/studentController")
@Transactional
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页 查询 学生信息
     * @param pageNum
     * @param pageSize
     * @param student
     * @return
     */
    @GetMapping("/getStudentByOpr/{pageNum}/{pageSize}")
    public Result getStudentsByOpr(@PathVariable Integer pageNum, @PathVariable Integer pageSize, Student student){
        Page<Student> page = new Page<>(pageNum, pageSize);
        IPage<Student> page1 = studentService.getStudentByOpr(page, student);
        return Result.ok(page);
    }

    /**
     * 添加 修改 学生信息
     * @param student
     * @return
     */
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        //对学生的密码进行加密
        if (!Strings.isEmpty(student.getPassword())) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    /**
     * 删除学生信息
     * @param ids
     * @return
     */
    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids) {
        studentService.removeByIds(ids);
        return Result.ok();
    }
}
