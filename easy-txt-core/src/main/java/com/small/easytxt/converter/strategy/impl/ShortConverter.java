package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;

import java.math.BigDecimal;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class ShortConverter implements Converter<Short> {

    public ShortConverter(){
        register(short.class, this);
        register(Short.class, this);
    }

    @Override
    public Short convertToJavaData(ConvertData convertData) {
        return new BigDecimal((String) convertData.getSource()).shortValue();
    }

    @Override
    public String convertToString(ConvertData convertData) {
        return String.valueOf(convertData.getSource());
    }
}
