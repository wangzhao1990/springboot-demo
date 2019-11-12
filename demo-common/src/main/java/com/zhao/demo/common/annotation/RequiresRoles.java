package com.zhao.demo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要某个或某些身份，才有权进行的操作。身份与身份之间可以有 AND 和 OR 的关系
 * For example,
 *
 * RequiresRoles("aRoleName")
 * void someMethod() {
 *     ...
 * }
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRoles {

    String[] value();

    Logical logical() default Logical.AND;
}