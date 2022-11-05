package com.small.easytxt.test.bigfile;

import com.small.easytxt.EasyTxt;
import com.small.easytxt.test.bean.BigBeanType;
import com.small.easytxt.test.listener.BigBeanTypeReadListener;
import com.small.easytxt.test.utils.FileSizeUtils;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/11/1 20:44
 * @version: v1.0
 */
public class BigFileTypeDemoTest {


    @Test
    public void test_write_type(){
        long start = System.currentTimeMillis();
        File file = new File("d:\\2_test_type_file.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo = 4;
        for (int i = 1; i <= maxPageNo ; i++) {
            int finalI = i;
            EasyTxt.write(file, BigBeanType.class, ",",i, 10, (pageNum, pageSize) -> {
                //模拟分页数据分批写入文件
                List<BigBeanType> list = new ArrayList<>(140000);
                for (int x=1; x <=3 ; x++){
                    BigBeanType bean = new BigBeanType();
                    bean.setColumn1('a');
                    bean.setColumn2((short)2);
                    bean.setColumn3((byte)3);
                    bean.setColumn4(4);
                    bean.setColumn5(5L);
                    bean.setColumn6(6.6F);
                    bean.setColumn7(7.7D);
                    bean.setColumn8(false);
                    bean.setColumn9('A');
                    bean.setColumn10((short)10);
                    bean.setColumn11((byte)11);
                    bean.setColumn12(12);
                    bean.setColumn13(13L);
                    bean.setColumn14(14.14F);
                    bean.setColumn15(15.15D);
                    bean.setColumn16(false);
                    bean.setColumn17(new BigInteger("17"));
                    bean.setColumn18(new BigDecimal("18.00"));
                    bean.setColumn19(new Date());
                    bean.setColumn20("文件操作完毕后必须关闭，否则长期保持对文件的连接状态，造成内存溢出的现象发生");
                    bean.setColumn21(LocalDate.now());
                    bean.setColumn22(LocalDateTime.now());
                    list.add(bean);
                }
                return list ;

            }).doWrite();
            System.out.println(" 文件大小是 "+ FileSizeUtils.fileSizeTransfer(FileSizeUtils.getSize(file.getAbsolutePath())));
        }
    }


    @Test
    public void test_write(){
        long start = System.currentTimeMillis();
        File file = new File("d:\\2_test_bigfile.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =80;
        for (int i = 1; i <= maxPageNo ; i++) {
            int finalI = i;
            EasyTxt.write(file, BigBeanType.class, ",",i, 10, (pageNum, pageSize) -> {
                //模拟分页数据分批写入文件
                List<BigBeanType> list = new ArrayList<>(140000);
                for (int x=1; x <=100000 ; x++){
                    BigBeanType bean = new BigBeanType();
                    bean.setColumn1('a');
                    bean.setColumn2((short)2);
                    bean.setColumn3((byte)3);
                    bean.setColumn4(4);
                    bean.setColumn5(5L);
                    bean.setColumn6(6.6F);
                    bean.setColumn7(7.7D);
                    bean.setColumn8(false);
                    bean.setColumn9('A');
                    bean.setColumn10((short)10);
                    bean.setColumn11((byte)11);
                    bean.setColumn12(12);
                    bean.setColumn13(13L);
                    bean.setColumn14(14.14F);
                    bean.setColumn15(15.15D);
                    bean.setColumn16(false);
                    bean.setColumn17(new BigInteger("17"));
                    bean.setColumn18(new BigDecimal("18.00"));
                    bean.setColumn19(new Date());
                    bean.setColumn20("文件操作完毕后必须关闭，否则长期保持对文件的连接状态，造成内存溢出的现象发生");
                    bean.setColumn21(LocalDate.now());
                    bean.setColumn22(LocalDateTime.now());
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
        System.out.println("写文件大约耗时" + totalSeconds+" 秒，约"+totalMinutes+"分 "+ currentSeconds + "秒");
    }




    @Test
    public void test_read(){
        long start = System.currentTimeMillis();
        File file = new File("d:\\2_test_bigfile.txt");
        EasyTxt.read(file, BigBeanType.class, ",", new BigBeanTypeReadListener()).doRead();
        long end = System.currentTimeMillis();
        //总秒数
        long totalSeconds = (end-start) / 1000;
        //当前秒数
        long currentSeconds = totalSeconds % 60;
        //总分钟
        long totalMinutes = totalSeconds / 60;
        System.out.println("读取2.52G的文件大约耗时" + totalSeconds+" 秒，约"+totalMinutes+"分 "+ currentSeconds + "秒");
        /**
         读取2.52G的文件大约耗时223 秒，约3分 43秒
         */
    }
}
