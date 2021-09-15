package com.yaiun.common.annotation;

import java.lang.annotation.*;

/**
 * 该注解，仅作标记作用，加上该注解后接口返回值将会被加密
 * 当作用在类上时，所有方法都会被加密
 * @Author raoyu
 * @Date 2020-07-20
 */

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypt {
}
