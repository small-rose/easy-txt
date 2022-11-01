package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.NumberUtils;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class BigDecimalConverter implements Converter<BigDecimal> {

    public BigDecimalConverter(){
        register(BigDecimal.class, this);
    }

    @Override
    public BigDecimal convertToJavaData(ConvertData convertData) {
        try {
            return NumberUtils.parseBigDecimal((String) convertData.getSource(), convertData);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new NumberFormatException("格式转换异常");
        }
    }

    @Override
    public String convertToString(ConvertData convertData) {
        try {
            return String.valueOf(NumberUtils.parseBigDecimal(String.valueOf( convertData.getSource()), convertData));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new NumberFormatException("格式转换异常");
        }
    }
}
