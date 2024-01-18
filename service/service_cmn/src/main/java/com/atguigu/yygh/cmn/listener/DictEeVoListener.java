package com.atguigu.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

public class DictEeVoListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;

    public DictEeVoListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        // 将读到的每一条数据都插入的数据库里面
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        if (!StringUtils.isEmpty(dict.getId())){
            // id不为空,修改
            this.dictMapper.updateById(dict);
        }else {
            this.dictMapper.insert(dict);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
