package com.small.easytxt.converter;

import lombok.Data;

import java.math.RoundingMode;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述： 数据转换类封装
 * @author: 张小菜
 * @date: 2022/10/30 18:14
 * @version: v1.0
 */
@Data
public class ConvertData {

    private Object source ;


    private String dateFormat ;
    private String numberFormat ;
    private RoundingMode roundingMode ;



}
