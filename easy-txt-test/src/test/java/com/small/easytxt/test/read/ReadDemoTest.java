package com.small.easytxt.test.read;

import cn.hutool.core.io.file.FileWriter;
import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.BeanTest;
import com.small.easytxt.test.DemoReadListener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ ReadDemoTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 15:20
 * @Version ： 1.0
 **/
public class ReadDemoTest {

    @Test
    public void test_18(){
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {

            StringBuffer line = new  StringBuffer();
            for (int j = 1; j < 5; j++) {
                line.append("测试数据L"+i+j).append("|");
            }

            data.add(line.substring(0, line.length()-1).toString());
        }
        File file = new File("d:\\2.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.appendLines(data);
    }

    @Test
    public void test_1() throws IOException {

        File file = new File("d:\\2.txt");
        EasyTxt.read(file, BeanTest.class,",", new DemoReadListener()).doRead();

    }

    @Test
    public void test_2() throws IOException {
        Integer pageSize = 2;
        File file = new File("d:\\2.txt");
        EasyTxt.read(file, BeanTest.class,",", pageSize, pagelist ->{
            pagelist.stream().forEach(System.out::println);
            System.out.println(pagelist.size());
        }).doRead();

    }
}
