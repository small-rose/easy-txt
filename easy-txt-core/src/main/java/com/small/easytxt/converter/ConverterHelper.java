package com.small.easytxt.converter;

import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.converter.strategy.impl.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 16:41
 * @version: v1.0
 */
public class ConverterHelper {

    private static Map<String , Converter> cacheConverter = new HashMap<>();


    public static void initConverters(Map<Integer, Field> beanFieldMap) {

        cacheConverter.put("int", new IntegerConverter());
        cacheConverter.put("bigInterger", new BigIntegerConverter());
        cacheConverter.put("long", new LongConverter());
        cacheConverter.put("byte", new ByteConverter());
        cacheConverter.put("char", new CharConverter());
        cacheConverter.put("float", new FloatConverter());
        cacheConverter.put("double", new DoubleConverter());
        cacheConverter.put("short", new ShortConverter());
        cacheConverter.put("boolean", new BooleanConverter());
        cacheConverter.put("BigDecimal", new BigDecimalConverter());
        cacheConverter.put("Date", new DateConverter());
        cacheConverter.put("String", new StringConverter());
    }
}
