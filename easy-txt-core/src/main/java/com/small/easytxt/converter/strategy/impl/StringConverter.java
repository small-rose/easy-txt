package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class StringConverter implements Converter<String> {

    public StringConverter(){
        register(String.class, this);
    }

    @Override
    public String convertToJavaData(ConvertData convertData) {

        return (String) convertData.getSource();
    }

    @Override
    public String convertToString(ConvertData convertData) {

        return String.valueOf(convertData.getSource());
    }
}
