package com.example.iherb.db.database;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String type() default "text";
    String reference() default "";
    boolean include() default true;
}
