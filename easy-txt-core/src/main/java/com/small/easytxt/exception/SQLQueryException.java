package com.small.easytxt.exception;

/**
 * 自定义SQL查询异常类，继承自RuntimeException
 * 用于处理与数据库查询操作相关的异常情况
 */
public class SQLQueryException extends RuntimeException{
    /**
     * 构造方法，创建一个带有错误信息的SQL查询异常
     * @param message 异常的详细描述信息
     */
    public SQLQueryException(String message) {
        super(message);
    }

    /**
     * 构造方法，创建一个带有错误信息和原因异常的SQL查询异常
     * @param message 异常的详细描述信息
     * @param throwable 导致此异常发生的原始异常
     */
    public SQLQueryException(String message,  Throwable throwable) {
        super(message, throwable);
    }
}
