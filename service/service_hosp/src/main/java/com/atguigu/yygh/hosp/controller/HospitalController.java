package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.hosp.bean.Actor;
import com.atguigu.yygh.hosp.bean.Result;
import com.atguigu.yygh.hosp.repository.ActorRepository;
import com.atguigu.yygh.hosp.repository.HospitalRepository;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.hosp.utils.HttpRequestHelper;
import com.atguigu.yygh.hosp.utils.MD5;
import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
@CrossOrigin
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/saveHospital")
    public Result saveHospital(HttpServletRequest request) throws Exception {
        // 将上传上来的参数类型进行转换
        Map<String, Object> map = HttpRequestHelper.SwitchMap(request.getParameterMap());
        // 取出hoscode和signkey进行校验，看看数据库是否存在数据，校验signkey
        String hoscode = (String)map.get("hoscode");
        String signKey = (String)map.get("sign");
        // 根据hoscode进行查询，看看数据库中有没有该条数据 有则更新，无则添加
        // signkey加密,然后和传递过来的值进行对比就行了
        String plateformSignKey = this.hospitalService.getSignKeyWithHoscode(hoscode);
        if (!StringUtils.isEmpty(hoscode) && !StringUtils.isEmpty(signKey) && MD5.encrypt(plateformSignKey).equals(signKey)){
            // 校验成功!可以添加
            String handlerLogoData = ((String)map.get("logoData")).replaceAll(" ", "+");
            map.put("logoData",handlerLogoData);
            this.hospitalService.saveHospital(map);
            return Result.ok();
        }else {
            throw new Exception();
        }

    }

    @PostMapping("/hospital/show")
    public Result<Hospital> show(HttpServletRequest request){
        Map<String, Object> map = HttpRequestHelper.SwitchMap(request.getParameterMap());
        // 然后就是去数据库中执行查询操作
        Hospital hoscode = this.hospitalService.getHospitalByHoscode((String) map.get("hoscode"));
        return Result.ok(hoscode);
    }
}
