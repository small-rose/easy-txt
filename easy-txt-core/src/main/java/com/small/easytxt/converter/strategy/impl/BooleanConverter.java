package com.small.easytxt.converter.strategy.impl;

import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.utils.NumberUtils;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class BooleanConverter implements Converter<Boolean> {

    public BooleanConverter(){
        register(boolean.class, this);
        register(Boolean.class, this);
    }

    @Override
    public Boolean convertToJavaData(ConvertData value) {
        return NumberUtils.parseBoolean((String) value.getSource(), value);
    }

    @Override
    public String convertToString(ConvertData value) {
        return String.valueOf(NumberUtils.parseBoolean((String) value.getSource(), value));
    }
}
