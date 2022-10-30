package com.small.easytxt.converter.factory;

import com.small.easytxt.converter.strategy.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:47
 * @version: v1.0
 */
public class ConverterFactory {

    private static Map<Class<?>, Converter<?>> converterMap = new HashMap<>();


    public static Converter<?> getConverterByType(Class<?> typeName){
        return converterMap.get(typeName);
    }

    public static void register(Class<?> clazz, Converter converter){
        if (!converterMap.containsKey(clazz) && converter != null){
            converterMap.put(clazz, converter);
        }
    }
}
