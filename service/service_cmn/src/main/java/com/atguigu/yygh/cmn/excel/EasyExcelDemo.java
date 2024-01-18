package com.atguigu.yygh.cmn.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.atguigu.yygh.vo.cmn.DictEeVo;

import java.util.ArrayList;
import java.util.List;


public class EasyExcelDemo {
    public static void main(String[] args) {
        EasyExcelDemo demo = new EasyExcelDemo();
        demo.read();
    }
    public void read(){
        // 报错的原因:没有无参构造器
        EasyExcel.read("C:\\Users\\admin\\Desktop\\world.xlsx", Student.class, new StudentListener()).sheet(1).doRead();
    }

    public void write(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"ll","男",22));
        students.add(new Student(2,"yf","女",22));
        students.add(new Student(3,"lx","男",22));
        students.add(new Student(4,"fy","男",22));
        // 这种方式只能往单个表格里面写入数据 因为最后调用了finish方法,表格关闭了
        // EasyExcel.write("C:\\Users\\admin\\Desktop\\hello.xlsx",Student.class).sheet("学生列表一").doWrite(students);


        //现在的这种可以往多个表格里面写入数据
        ExcelWriter build = EasyExcel.write("C:\\Users\\admin\\Desktop\\world.xlsx", Student.class).build();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "学生列表1").build();
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "学生列表2").build();
        build.write(students,sheet1);
        for (Student student : students) {
            student.setName("long");
        }
        build.write(students,sheet2);
        build.finish(); // 记得一定要关闭这个东西,否则这个东西不好使
    }


}
