package com.small.easytxt.test.bean;

import com.small.easytxt.annotation.TxtFiled;
import com.small.easytxt.annotation.TxtPorperty;
import lombok.Data;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/9 10:42
 * @version: v1.0
 */
@Data
@TxtPorperty(fixIndex = false)
public class BeanTestVO {

    @TxtFiled( index = 1 )
    private String data1;

    @TxtFiled( index = 8 )
    private String data2;

    @TxtFiled( index = 13 )
    private String data3;

    @TxtFiled( index = 14 )
    private String data4;

    @TxtFiled( index = 18 )
    private String data5;

    @TxtFiled( index = 20 )
    private String data6;

}
