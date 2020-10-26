package com.edu.ckl.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //接口、类、枚举、注解
@Retention(RetentionPolicy.RUNTIME) //RetentionPolicy.RUNTIME:这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用。
public @interface Controller {
    String value() default "";
}
