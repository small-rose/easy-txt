package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.NumberUtils;

import java.math.BigInteger;
import java.text.ParseException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class BigIntegerConverter implements Converter<BigInteger> {

    public BigIntegerConverter(){
        register(BigInteger.class, this);
    }

    @Override
    public BigInteger convertToJavaData(ConvertData convertData) {

        try {

            return NumberUtils.parseBigInteger((String) convertData.getSource(), convertData);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new NumberFormatException("格式转换异常");
        }
    }

    @Override
    public String convertToString(ConvertData convertData) {

        try {
            return String.valueOf(NumberUtils.parseBigInteger((String) convertData.getSource(), convertData));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new NumberFormatException("格式转换异常");
        }
    }
}
