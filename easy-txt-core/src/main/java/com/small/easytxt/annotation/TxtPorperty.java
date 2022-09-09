package com.small.easytxt.annotation;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TxtPorperty {

    /**
     * 强制固定索引列
     * @return
     */
    boolean fixIndex() default true ;
}
