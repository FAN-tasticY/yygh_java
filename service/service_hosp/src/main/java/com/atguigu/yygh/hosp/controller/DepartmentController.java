package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.hosp.bean.Result;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.hosp.utils.HttpRequestHelper;
import com.atguigu.yygh.model.hosp.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/list")
    public Result list(HttpServletRequest request){
        Map<String, Object> map = HttpRequestHelper.SwitchMap(request.getParameterMap());
        String hoscode = (String)map.get("hoscode");
        // 忘记了么?他是带着分页查询的啊
        int page = (int) map.get("page");
        int limit = (int) map.get("limit");
        Department department = this.departmentService.findByHoscodeAndPage(hoscode,page,limit);
        return Result.ok(department);
    }
}
