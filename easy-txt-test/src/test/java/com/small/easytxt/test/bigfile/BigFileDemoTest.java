package com.small.easytxt.test.bigfile;

import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.bean.BigBean;
import com.small.easytxt.test.listener.BigBeanReadListener;
import com.small.easytxt.test.utils.FileSizeUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/11/1 20:44
 * @version: v1.0
 */
public class BigFileDemoTest {



    @Test
    public void test_write(){
        long start = System.currentTimeMillis();
        File file = new File("d:\\1_test_bigfile.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =200;
        for (int i = 1; i <= maxPageNo ; i++) {
            int finalI = i;
            EasyTxt.write(file, BigBean.class, ",",i, 10, (pageNum, pageSize) -> {
                //模拟分页数据分批写入文件
                List<BigBean> list = new ArrayList<>();
                for (int x=1; x <=10000 ; x++){
                    BigBean bean = new BigBean();
                    bean.setColumn1("这个是第"+ finalI +"页第"+x+"行第1列的数据");
                    bean.setColumn2("这个是第"+ finalI +"页第"+x+"行第2列的数据");
                    bean.setColumn3("这个是第"+ finalI +"页第"+x+"行第3列的数据");
                    bean.setColumn4("这个是第"+ finalI +"页第"+x+"行第4列的数据");
                    bean.setColumn5("这个是第"+ finalI +"页第"+x+"行第5列的数据");
                    bean.setColumn6("这个是第"+ finalI +"页第"+x+"行第6列的数据");
                    bean.setColumn7("这个是第"+ finalI +"页第"+x+"行第7列的数据");
                    bean.setColumn8("这个是第"+ finalI +"页第"+x+"行第8列的数据");
                    bean.setColumn9("这个是第"+ finalI +"页第"+x+"行第9列的数据");
                    bean.setColumn10("这个是第"+ finalI +"页第"+x+"行第10列的数据");
                    bean.setColumn11("这个是第"+ finalI +"页第"+x+"行第11列的数据");
                    bean.setColumn12("这个是第"+ finalI +"页第"+x+"行第12列的数据");
                    bean.setColumn13("这个是第"+ finalI +"页第"+x+"行第13列的数据");
                    bean.setColumn14("这个是第"+ finalI +"页第"+x+"行第14列的数据");
                    bean.setColumn15("这个是第"+ finalI +"页第"+x+"行第15列的数据");
                    bean.setColumn16("这个是第"+ finalI +"页第"+x+"行第16列的数据");
                    bean.setColumn17("这个是第"+ finalI +"页第"+x+"行第17列的数据");
                    bean.setColumn18("这个是第"+ finalI +"页第"+x+"行第18列的数据");
                    bean.setColumn19("这个是第"+ finalI +"页第"+x+"行第19列的数据");
                    bean.setColumn20("这个是第"+ finalI +"页第"+x+"行第20列的数据");
                    bean.setColumn21("这个是第"+ finalI +"页第"+x+"行第21列的数据");
                    bean.setColumn22("这个是第"+ finalI +"页第"+x+"行第22列的数据");
                    bean.setColumn23("这个是第"+ finalI +"页第"+x+"行第23列的数据");
                    bean.setColumn24("这个是第"+ finalI +"页第"+x+"行第24列的数据");
                    bean.setColumn25("这个是第"+ finalI +"页第"+x+"行第25列的数据");
                    bean.setColumn26("这个是第"+ finalI +"页第"+x+"行第26列的数据");
                    bean.setColumn27("这个是第"+ finalI +"页第"+x+"行第27列的数据");
                    bean.setColumn28("这个是第"+ finalI +"页第"+x+"行第28列的数据");
                    bean.setColumn29("这个是第"+ finalI +"页第"+x+"行第29列的数据");
                    bean.setColumn30("这个是第"+ finalI +"页第"+x+"行第30列的数据");


                    list.add(bean);
                }
                return list ;

            }).doWrite();
            System.out.println(" 文件大小是 "+ FileSizeUtils.fileSizeTransfer(FileSizeUtils.getSize(file.getAbsolutePath())));
        }
        long end = System.currentTimeMillis();
        //总秒数
        long totalSeconds = (end-start) / 1000;
        //当前秒数
        long currentSeconds = totalSeconds % 60;
        //总分钟
        long totalMinutes = totalSeconds / 60;
        System.out.println("写个2.52G的文件大约耗时" + totalSeconds+" 秒，约"+totalMinutes+"分 "+ currentSeconds + "秒");
        //写个2.52G的文件大约耗时111秒，约1分51秒
    }




    @Test
    public void test_read(){
        long start = System.currentTimeMillis();
        File file = new File("d:\\1_test_bigfile.txt");
        EasyTxt.read(file, BigBean.class, ",", new BigBeanReadListener()).doRead();
        long end = System.currentTimeMillis();
        //总秒数
        long totalSeconds = (end-start) / 1000;
        //当前秒数
        long currentSeconds = totalSeconds % 60;
        //总分钟
        long totalMinutes = totalSeconds / 60;
        System.out.println("读取2.52G的文件大约耗时" + totalSeconds+" 秒，约"+totalMinutes+"分 "+ currentSeconds + "秒");
        /**
        File read over : d:\1_test_bigfile.txt
        写个2.52G的文件大约耗时105 秒，约1分 45秒
         */
    }
}
