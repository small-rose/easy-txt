package com.small.easytxt.write.listener;

import java.util.List;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ ReadListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public interface WriteListener<T> {


    /**
     * 写行
     */
    List<T> lineInvoke();


}
