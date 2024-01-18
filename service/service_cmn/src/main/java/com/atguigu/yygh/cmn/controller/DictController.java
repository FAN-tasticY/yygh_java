package com.atguigu.yygh.cmn.controller;


import com.alibaba.excel.EasyExcel;
import com.atguigu.yygh.cmn.listener.DictEeVoListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.cmn.service.impl.DictServiceImpl;
import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.deploy.net.HttpResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组织架构表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2024-01-16
 */
@RestController
@RequestMapping("/admin/cmn")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;
    @Autowired
    private DictMapper dictMapper;

    @GetMapping("/childList/{pid}")
    public R getListByPid(@PathVariable Long pid){
        QueryWrapper<Dict> dictQueryWrapper1 = new QueryWrapper<>();
        dictQueryWrapper1.eq("parent_id",pid);
        List<Dict> list = this.dictService.list(dictQueryWrapper1);
        for (Dict dict : list) {
            QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
            dictQueryWrapper.eq("parent_id",dict.getId());
            int count = this.dictService.count(dictQueryWrapper);
            dict.setHasChildren(count > 0);
        }
        return R.ok().data("dict",list);
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        // 所谓的下载就是将数据写入到表格中是不是
        List<Dict> list = this.dictService.list();
        List<DictEeVo> dictEeVoList = new ArrayList<>();

        for (Dict dict : list) {
            DictEeVo dictEeVo = new DictEeVo();
            BeanUtils.copyProperties(dict,dictEeVo);
            dictEeVoList.add(dictEeVo);
        }
        // 设置响应头信息
        response.setContentType("application/vnd.ms-excel");    // excel文件
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("字典数据下载","UTF-8");
        // 作为一个附件,不要让浏览器解析它
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");


        // 以响应流的形式,给前端进行数据的响应就行了 如果是往磁盘里面写的方式的话,那么是往服务器里面写
        EasyExcel.write(response.getOutputStream(), DictEeVo.class)
                .sheet("医院数据").doWrite(dictEeVoList);
    }

    @PostMapping("/upload")
    public R download(MultipartFile file) throws IOException {
        // 文件上传,前端传递过来文件,然后上传到数据库里面
        EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictEeVoListener(this.dictMapper)).sheet(0).doRead();
        return R.ok();
    }

}

