package com.small.easytxt.test.bean;

import com.small.easytxt.annotation.TxtFiled;
import lombok.Data;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/9 10:42
 * @version: v1.0
 */
@Data
public class BeanTest {

    @TxtFiled( index = 1 )
    private String line1;

    @TxtFiled( index = 2 )
    private String line2;

    @TxtFiled( index = 30 )
    private String line3;

    @TxtFiled( index = 4 )
    private String line4;
}
