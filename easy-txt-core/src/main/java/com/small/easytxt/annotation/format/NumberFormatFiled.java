package com.small.easytxt.annotation.format;

import java.lang.annotation.*;
import java.math.RoundingMode;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NumberFormatFiled {

    /**
     *
     * Specific format reference {@link java.text.DecimalFormat}
     *
     * @return Format pattern
     */
    String value() default "";

    /**
     * Rounded by default
     *
     * @return RoundingMode
     */
    RoundingMode roundingMode() default RoundingMode.HALF_UP;
}
