package com.small.easytxt.annotation.format;


import java.lang.annotation.*;

@Target( ElementType.FIELD)
@Retention( RetentionPolicy.RUNTIME)
@Inherited
public @interface DateFormatFiled {
    /**
     *
     * Specific format reference {@link java.text.SimpleDateFormat}
     *
     * @return Format pattern
     */
    String value() default  "";

}
