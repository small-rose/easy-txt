package com.small.easytxt.metadata;

import com.small.easytxt.read.listener.ReadListener;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ AbstractReadListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 11:00
 * @Version ： 1.0
 **/
public abstract class AbstractReadListener<T> implements ReadListener<T> {
    /**
     * 预留钩子
     * 读行前
     */
    public void lineBefore(T line) {
    }



    /**
     * 预留钩子
     * 读行候
     */
    public void lineAfter(T line) {
    }
}
