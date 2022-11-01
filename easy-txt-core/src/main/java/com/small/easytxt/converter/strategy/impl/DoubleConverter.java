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
public class DoubleConverter implements Converter<Double> {

    public DoubleConverter(){
        register(double.class, this);
        register(Double.class, this);
    }

    @Override
    public Double convertToJavaData(ConvertData convertData) {
        try {
            return NumberUtils.parseDouble((String) convertData.getSource(), convertData);
        } catch (ParseException e) {
            throw new NumberFormatException("Double格式转换异常");
        }
    }

    @Override
    public String convertToString(ConvertData convertData) {
        try {
            return String.valueOf(NumberUtils.parseDouble(String.valueOf( convertData.getSource()), convertData));
        } catch (ParseException e) {
            throw new NumberFormatException("Double格式转换异常");
        }
    }
}
