package com.small.easytxt.test;

import cn.hutool.core.io.file.FileWriter;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ TestFileGenerator ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 12:16
 * @Version ： 1.0
 **/
public class TestFileGenerator {

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
}
