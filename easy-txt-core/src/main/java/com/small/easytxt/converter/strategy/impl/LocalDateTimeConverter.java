package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {



    public LocalDateTimeConverter(){
         register(LocalDateTime.class, this);
    }

    @Override
    public LocalDateTime convertToJavaData(ConvertData convertData) {
        return DateUtils.parseLocalDateTime((String) convertData.getSource(), convertData.getDateFormat(), Locale.CHINESE);
    }

    @Override
    public String convertToString(ConvertData convertData) {

        return DateUtils.format((LocalDateTime) convertData.getSource(), convertData.getDateFormat(), Locale.getDefault());
    }
}
