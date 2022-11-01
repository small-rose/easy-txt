package com.small.easytxt.test.bean;

import com.small.easytxt.annotation.TxtFiled;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/11/1 20:45
 * @version: v1.0
 */
@Data
public class BigBeanType {

    @TxtFiled( index = 0 )
    private char column1 ;
    @TxtFiled( index = 1 )
    private short column2 ;
    @TxtFiled( index = 2 )
    private byte column3 ;
    @TxtFiled( index = 3 )
    private int column4 ;
    @TxtFiled( index = 4 )
    private long column5 ;
    @TxtFiled( index = 5 )
    private float column6 ;
    @TxtFiled( index = 6 )
    private double column7 ;
    @TxtFiled( index = 7 )
    private boolean column8 ;
    @TxtFiled( index = 8 )
    private Character column9 ;
    @TxtFiled( index = 9 )
    private Short column10 ;
    @TxtFiled( index = 10 )
    private Byte column11 ;
    @TxtFiled( index = 11 )
    private Integer column12 ;
    @TxtFiled( index = 12 )
    private Long column13 ;
    @TxtFiled( index = 13 )
    private Float column14 ;
    @TxtFiled( index = 14 )
    private Double column15 ;
    @TxtFiled( index = 15 )
    private Boolean column16 ;
    @TxtFiled( index = 16 )
    private BigInteger column17 ;
    @TxtFiled( index = 17 )
    private BigDecimal column18 ;
    @TxtFiled( index = 18 )
    private String column19 ;
    @TxtFiled( index = 19 )
    private String column20 ;

}
