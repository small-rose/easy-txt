package com.small.easytxt.executor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ FileReadExecutor ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/8 11:03
 * @Version ： 1.0
 **/
public interface FileExecutor {

    /**
     * 执行方法
     * 该方法用于执行某个操作，可能会抛出IO异常
     *
     * @throws IOException 如果发生I/O错误，则抛出此异常
     */
    void execute() throws IOException;


    /**
     * 首行 添加 BEG
     *
     * @param file
     */
    void addBegin(File file);

    /**
     * 首行 添加 BEG
     *
     * @param file
     */
    void addEnd(File file);


    <T> void execute(File file, List<T> data) throws IOException;


    List<File> getFileNameList();

    /**
     * 删除模板文件
     */
    void delTemplate();
}
