package com.small.easytxt.test.read;

import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.bean.BeanTest;
import com.small.easytxt.test.bean.BeanTestVO;
import com.small.easytxt.test.listener.BeanReadListener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ BeanReadDemoTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 12:15
 * @Version ： 1.0
 **/
public class BeanReadDemoTest {



    @Test
    public void test_simple_bean() throws IOException {
        File file = new File("d:\\2.txt");
        EasyTxt.read(file, BeanTest.class, ",", new BeanReadListener()).doRead();
    }

    @Test
    public void test_page_bean() throws IOException {
        //每次读取条件
        Integer pageSize = 2;
        File file = new File("d:\\2.txt");
        //Bean 属性多少于或等于数据列
        EasyTxt.read(file, BeanTest.class, "\\|", pageSize, (pagelist) -> {
            pagelist.stream().forEach(System.out::println);
            System.out.println(pagelist.size());
        }).doRead();
    }

    @Test
    public void test_3() throws IOException {
        Integer pageSize = 2;
        File file = new File("d:\\2.txt");
        //Bean 属性多余数据列
        EasyTxt.read(file, BeanTestVO.class, "\\|", pageSize, pagelist -> {
            pagelist.stream().forEach(System.out::println);
            System.out.println(pagelist.size());
        }).doRead();
    }
}
