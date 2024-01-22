package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.mapper.HospitalSetMapper;
import com.atguigu.yygh.hosp.repository.HospitalRepository;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalSetMapper hospitalSetMapper;
    @Override
    public void saveHospital(Map<String, Object> resultMap) {
        // 将map类型数据解析为hospital对象类型
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(resultMap), Hospital.class);
        String hoscode = hospital.getHoscode();
        Hospital mongodbHospital = this.hospitalRepository.findByHoscode(hoscode);
        if (mongodbHospital == null){
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            this.hospitalRepository.save(hospital);
        }else {
            // 如果是修改的话,那么我们需要携带上id
            hospital.setId(mongodbHospital.getId());
            hospital.setStatus(mongodbHospital.getStatus());
            hospital.setCreateTime(mongodbHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(mongodbHospital.getIsDeleted());
            this.hospitalRepository.save(hospital);
        }

    }

    @Override
    public String getSignKeyWithHoscode(String requestHoscode) {
        // 根据hoscode进行查询，看看这个医院是否存在，然后去我们这边的医院表中查询
        // hosmapper是我们的mysql数据库表
        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(requestHoscode)){
            hospitalSetQueryWrapper.eq("hoscode",requestHoscode);
            HospitalSet hospitalSet = this.hospitalSetMapper.selectOne(hospitalSetQueryWrapper);
            if (hospitalSet != null){
                return hospitalSet.getSignKey();
            }else {
                return "该医院不存在";
            }
        }else {
            return "医院编码不能为空!";
        }

    }

    @Override
    public Hospital getHospitalByHoscode(String hoscode) {
        return this.hospitalRepository.findByHoscode(hoscode);
    }
}
