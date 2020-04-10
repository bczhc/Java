package pers.zhc.u.common;

import java.lang.annotation.*;

public class Documents {
    @Documented
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
    public @interface Nullable {
        String value() default "";
    }

    @Documented
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
    public @interface NotNull {
        String value() default "";
    }
}