package com.small.easytxt.write.listener;

import com.small.easytxt.metadata.FileWriterContext;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ ReadListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public interface TxtWriteListener<T> {

    /**
     *
     * @param object
     * @return
     */
    boolean invokeLine(T object);

    /**
     *
     * @param object
     */
    void doAfterWrite(Object object);

    /**
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    void onException(Exception exception, FileWriterContext context) throws Exception ;
}
