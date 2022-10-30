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
public class IntegerConverter implements Converter<Integer> {

    public IntegerConverter(){
        register(int.class, this);
        register(Integer.class, this);
    }

    @Override
    public Integer convertToJavaData(ConvertData convertData) {

        try {
            return NumberUtils.parseInteger((String)convertData.getSource(), convertData);
        } catch (ParseException e) {
            throw new NumberFormatException("Byte格式转换异常");
        }

    }

    @Override
    public String convertToString(ConvertData convertData) {

        return String.valueOf(convertData.getSource());
    }
}
