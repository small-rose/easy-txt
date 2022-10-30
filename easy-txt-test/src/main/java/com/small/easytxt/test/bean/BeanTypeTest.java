package com.small.easytxt.test.bean;

import com.small.easytxt.annotation.TxtFiled;
import com.small.easytxt.annotation.format.DateFormatFiled;
import com.small.easytxt.annotation.format.NumberFormatFiled;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 16:20
 * @version: v1.0
 */
@Data
public class BeanTypeTest {

    @TxtFiled( index = 0 )
    private String name;

    @TxtFiled( index = 1 )
    private int age;

    @TxtFiled( index = 2 )
    private BigDecimal money;

    @TxtFiled( index = 3 )
    private Long accNo;


    @TxtFiled( index = 4 )
    @NumberFormatFiled(value = "## #.##")
    private Double height;

    @TxtFiled( index = 5 )
    @DateFormatFiled(value = "yyyy-MM-dd")
    private Date birth;
}
