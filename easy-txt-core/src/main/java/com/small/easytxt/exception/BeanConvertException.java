package com.small.easytxt.exception;

/**
 * @Project: easy-txt
 * @Author: 张小菜
 * @Description: [ BeanConvertException ] 说明： 无
 * @Function: 功能描述： 无
 * @Date: 2024/7/6 006 11:43
 * @Version: v1.0
 */
public class BeanConvertException  extends RuntimeException{

    public BeanConvertException(String message) {
        super(message);
    }

    public BeanConvertException(String message,  Throwable throwable) {
        super(message, throwable);
    }
}
