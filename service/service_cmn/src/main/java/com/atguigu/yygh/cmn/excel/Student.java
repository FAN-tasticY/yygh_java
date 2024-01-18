package com.atguigu.yygh.cmn.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data   // 这个东西只是提供get和set方法么？
public class Student {
    @ExcelProperty("学生id")
    public int id;
    @ExcelProperty("学生姓名")
    @ColumnWidth(60)
    public String name;
    @ExcelProperty("学生性别")
    public String gender;
    public int age;

    public Student() {
    }

    public Student(int id, String name, String gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
