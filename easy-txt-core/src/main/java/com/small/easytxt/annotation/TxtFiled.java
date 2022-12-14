package com.small.easytxt.annotation;


import java.lang.annotation.*;

/**
 * @author small
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TxtFiled {

    /**
     * 行转bean列索引
     * @return
     */
    int index() default -1;


}
