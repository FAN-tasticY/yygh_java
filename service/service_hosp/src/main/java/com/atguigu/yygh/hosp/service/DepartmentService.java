package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Department;

public interface DepartmentService {

    Department findByHoscodeAndPage(String hoscode,int page,int limit);
}
