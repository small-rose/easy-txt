package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.DateUtils;

import java.time.LocalDate;
import java.util.Locale;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class LocalDateConverter implements Converter<LocalDate> {



    public LocalDateConverter(){
         register(LocalDate.class, this);
    }

    @Override
    public LocalDate convertToJavaData(ConvertData convertData) {
        return DateUtils.parseLocalDate((String) convertData.getSource(), convertData.getDateFormat(), Locale.CHINESE);
    }

    @Override
    public String convertToString(ConvertData convertData) {

        return DateUtils.format((LocalDate) convertData.getSource(), convertData.getDateFormat(), Locale.getDefault());
    }
}
