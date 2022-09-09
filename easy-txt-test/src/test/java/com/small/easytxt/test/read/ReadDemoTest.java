package com.small.easytxt.test.read;

import cn.hutool.core.io.file.FileWriter;
import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.bean.BeanTest;
import com.small.easytxt.test.listener.DemoReadListener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/9 10:44
 * @version: v1.0
 */
public class ReadDemoTest {

    @Test
    public void test_18() {
        List<String> data = new ArrayList();

        for(int i = 1; i <= 5; ++i) {
            StringBuffer line = new StringBuffer();

            for(int j = 1; j < 5; ++j) {
                line.append("测试数据L" + i + j).append("|");
            }

            data.add(line.substring(0, line.length() - 1).toString());
        }

        File file = new File("d:\\2.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.appendLines(data);
    }

    @Test
    public void test_1() throws IOException {
        File file = new File("d:\\2.txt");
        EasyTxt.read(file, BeanTest.class, ",", new DemoReadListener()).doRead();
    }

    @Test
    public void test_2() throws IOException {
        Integer pageSize = 2;
        File file = new File("d:\\2.txt");
        EasyTxt.read(file, BeanTest.class, ",", pageSize, (pagelist) -> {
            Stream var10000 = pagelist.stream();
            PrintStream var10001 = System.out;
            var10000.forEach(var10001::println);
            System.out.println(pagelist.size());
        }).doRead();
    }
}
