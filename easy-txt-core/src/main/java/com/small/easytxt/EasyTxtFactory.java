package com.small.easytxt;


import com.small.easytxt.listener.PageReadConsumerListener;
import com.small.easytxt.listener.ReadListener;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ EasyTxt ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 15:43
 * @Version ： 1.0
 **/
public class EasyTxtFactory {

    public static TxtFileReader read(File file) {
        return read(file, null, null, null);
    }

    public static TxtFileReader read(File file, ReadListener readListener) {
        return read(file, null, null,readListener);
    }


    public static TxtFileReader read(File file, Class bean, String splitor, ReadListener readListener) {
        TxtFileReader txtFileReader = new TxtFileReader();
        txtFileReader.file(file);
        if (bean != null) {
            txtFileReader.bean(bean);
        }
        if (splitor != null) {
            txtFileReader.split(splitor);
        }
        if (readListener != null) {
            txtFileReader.registerReadListener(readListener);
        }
        return txtFileReader;
    }


    public static <T> TxtFileReader read(File file, Class<T> bean, String splitor, int pageSize, Consumer<List<T>> consumer) {
        TxtFileReader txtFileReader = new TxtFileReader();
        txtFileReader.file(file);
        if (bean != null) {
            txtFileReader.bean(bean);
        }
        if (splitor != null) {
            txtFileReader.split(splitor);
        }
        if (consumer != null) {
            txtFileReader.registerReadListener(new PageReadConsumerListener(pageSize, consumer));
        }
        return txtFileReader;
    }
}
