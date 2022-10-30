package com.small.easytxt.converter.strategy;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.factory.ConverterFactory;

public interface Converter<T> {

    default void register(Class clazz, Converter converter){
        ConverterFactory.register(clazz, converter);
    }
    /**
     *  将字符串值转换为对应的Java类型
     * @param value
     * @return
     */
    T convertToJavaData(ConvertData value);

    /**
     *  将对应的Java类型 转换为 字符串值
     * @param value
     * @return
     */
    String convertToString(ConvertData value);
}
