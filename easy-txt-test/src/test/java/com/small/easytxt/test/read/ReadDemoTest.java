package com.small.easytxt.test.read;

import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.listener.DemoReadListener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/9 10:44
 * @version: v1.0
 */
public class ReadDemoTest {



    @Test
    public void test_1() throws IOException {
        File file = new File("d:\\2.txt");
        EasyTxt.read(file,  new DemoReadListener()).doRead();
    }



}
