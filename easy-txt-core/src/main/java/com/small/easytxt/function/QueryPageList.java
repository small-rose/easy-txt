package com.small.easytxt.function;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ QueryPageList ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 16:19
 * @Version ： 1.0
 **/
@FunctionalInterface
public interface QueryPageList<T> {

    public T query(int pageNum, int pageSize);
}
