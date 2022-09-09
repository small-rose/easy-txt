package com.small.easytxt.read.listener;

import java.io.File;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ ReadListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public interface ReadListener<T> {




    /**
     * 读行
     */
    void lineInvoke(Integer rowNo, T data);


    /**
     * 文件结束
     * @param file
     */
    void fileEnd(File file);
}
