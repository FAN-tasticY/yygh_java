package com.atguigu.yygh.hosp.utils;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestHelper {
    public static Map<String,Object> SwitchMap(Map<String,String[]> map){
        Map<String, Object> stringHashMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            stringHashMap.put(entry.getKey(),entry.getValue()[0]);
        }
        return stringHashMap;
    }
}
