package com.atguigu.yygh.hosp.controller;


import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 医院设置表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2024-01-08
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @GetMapping("/findAll")
    public R findAllHospSet(){
        // 左边是类型，不是对象！
        List<HospitalSet> list = hospitalSetService.list();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",list);
        return R.ok().data(map);
    }

    // 分页及带条件查询
    @PostMapping("/pageHelper/{num}/{size}")        //post请求,路由里面携带参数,然后再携带一个请求体参数 然后就是这么写的!
    public R pagination(@PathVariable Integer num, @PathVariable Integer size, @RequestBody HospitalSetQueryVo hospitalSetQueryVo){
        Page<HospitalSet> page = new Page<HospitalSet>(num,size);
        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHosname())){
            hospitalSetQueryWrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        // hoscode是精准查询，如果不知道code就不要传递进来
        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHoscode())){
            hospitalSetQueryWrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }

        hospitalSetService.page(page,hospitalSetQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",page.getRecords());
        map.put("total",page.getTotal());
        return R.ok().data(map);
    }

    @PostMapping("/batchDelete")
    public R batchDelete(@RequestBody List<Integer> ids){
        this.hospitalSetService.removeByIds(ids);
        return R.ok();
    }

    @GetMapping("/oneDelete/{id}")
    public R oneDelete(@PathVariable Long id){
        this.hospitalSetService.removeById(id);
        return R.ok();
    }

    @GetMapping("/changeStatus/{id}/{status}")
    public R changeStatus(@PathVariable Long id,@PathVariable Integer status){
        HospitalSet hospitalSet = new HospitalSet();
        hospitalSet.setId(id);
        hospitalSet.setStatus(status);
        this.hospitalSetService.updateById(hospitalSet);
        return R.ok();
    }
}

