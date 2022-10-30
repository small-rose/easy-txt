package com.small.easytxt.test.read;

import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.bean.BeanTypeTest;
import com.small.easytxt.test.listener.BeanTypeReadListener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述： 进行类型转换测试
 * @author: 张小菜
 * @date: 2022/10/30 16:15
 * @version: v1.0
 */
public class BeanTypeDemoTest {

    @Test
    public void test_25(){

        File file = new File("d:\\1_typetest_bean_write.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;
        for (int i = 1; i <= maxPageNo ; i++) {
            EasyTxt.write(file, BeanTypeTest.class, ",",i, 10, (pageNum, pageSize) -> {
                //模拟分页数据分批写入文件
                List<BeanTypeTest> list = new ArrayList<>();
                for (int x=0; x <10 ; x++){
                    BeanTypeTest bean = new BeanTypeTest();
                    bean.setName("A-"+pageNum+x);
                    bean.setAge(x);
                    bean.setMoney(new BigDecimal(pageNum+x));
                    bean.setAccNo((long) (100+pageNum+x));
                    bean.setHeight(new Double(10000 / (x + 1)));
                    bean.setBirth(new Date());
                    list.add(bean);
                }
                return list ;
            }).doWrite();
        }
    }
    @Test
    public void test_47() throws IOException {

        File file = new File("d:\\1_typetest_bean_write.txt");
        EasyTxt.read(file, BeanTypeTest.class, ",", new BeanTypeReadListener()).doRead();
    }
}
