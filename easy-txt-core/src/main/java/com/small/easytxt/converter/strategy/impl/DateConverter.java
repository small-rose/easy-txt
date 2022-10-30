package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class DateConverter implements Converter<Date> {



    public DateConverter(){
         register(Date.class, this);
    }

    @Override
    public Date convertToJavaData(ConvertData convertData) {
        try {
            return DateUtils.parseDate((String) convertData.getSource(), convertData.getDateFormat());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("不支持的日期格式转换");
        }
    }

    @Override
    public String convertToString(ConvertData convertData) {

        return DateUtils.format((Date) convertData.getSource(), convertData.getDateFormat());
    }
}
