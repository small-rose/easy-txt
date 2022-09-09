package com.small.easytxt.exception;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ FiledIndexException ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 11:47
 * @Version ： 1.0
 **/
public class FiledIndexException extends RuntimeException{
    public FiledIndexException(String message) {
        super(message);
    }

    public FiledIndexException(String message,  Throwable throwable) {
        super(message, throwable);
    }
}
