package com.small.easytxt.test.write;

import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.bean.BeanTest;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ BeanWriteDemoTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 13:52
 * @Version ： 1.0
 **/
public class BeanWriteDemoTest {


    @Test
    public void test_bean_write(){

        File file = new File("d:\\1_test_bean_write.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;
        for (int i = 1; i <= maxPageNo ; i++) {
            EasyTxt.write(file, BeanTest.class, ",",i, 10, (pageNum, pageSize) -> {
                //模拟分页数据分批写入文件
                List<BeanTest> list = new ArrayList<>();
                for (int x=0; x <10 ; x++){
                    BeanTest bean = new BeanTest();
                    bean.setLine1("A-"+pageNum+x);
                    bean.setLine2("X01-"+pageNum+x);
                    bean.setLine3("测试-"+pageNum+x);
                    bean.setLine4("100-"+pageNum+x);
                    list.add(bean);
                }
                return list ;
            }).doWrite();
        }

    }
}
