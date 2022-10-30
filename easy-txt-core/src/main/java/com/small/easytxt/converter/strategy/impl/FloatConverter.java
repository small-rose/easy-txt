package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.NumberUtils;

import java.text.ParseException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class FloatConverter implements Converter<Float> {

    public FloatConverter(){
        register(float.class, this);
        register(Float.class, this);
    }

    @Override
    public Float convertToJavaData(ConvertData convertData) {
        try {
            return NumberUtils.parseFloat((String) convertData.getSource(), convertData);
        } catch (ParseException e) {
            throw new NumberFormatException("Byte格式转换异常");
        }

    }

    @Override
    public String convertToString(ConvertData convertData) {
        try {
            return String.valueOf(NumberUtils.parseFloat((String) convertData.getSource(), convertData));
        } catch (ParseException e) {
            throw new NumberFormatException("Byte格式转换异常");
        }
    }
}
