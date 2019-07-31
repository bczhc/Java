package com.zhc.u.common;

import java.lang.annotation.*;

public class Documents {
    @Documented
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
    public @interface Nullable {
    }
}
