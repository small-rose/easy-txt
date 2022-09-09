package com.small.easytxt.annotation;


import java.lang.annotation.*;

/**
 * @author small
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TxtFiled {

    int index() default -1;

}
