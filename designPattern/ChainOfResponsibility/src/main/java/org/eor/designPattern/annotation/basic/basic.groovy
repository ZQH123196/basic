package org.eor.designPattern.annotation.basic

import java.lang.annotation.Annotation
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 体现注解的基本使用，默认值，获取赋值以及默认绑定行为
 */

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationOne {
    String when();

    String value() default "default MyAnnotationOne value";
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationTwo {
    String when() default "MyAnnotationTwo";

    String value() default "default MyAnnotationTwo value";
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationThree {
    String when() default "MyAnnotationThree";

    String value() default "default MyAnnotationThree value";
}

@MyAnnotationOne(when = "8999") // 没有 default 的必须显式绑定
@MyAnnotationTwo("8999") // 隐式默认绑定到 value() 属性上
@MyAnnotationThree
class SomeClassOne {}

public void printAnnotation(Annotation annotation) {
    if (annotation != null) {
        println("${annotation}")
    }
}

MyAnnotationOne myAnnotationOne = SomeClassOne.getAnnotation(MyAnnotationOne.class)
printAnnotation(myAnnotationOne)

MyAnnotationTwo myAnnotationTwo = SomeClassOne.getAnnotation(MyAnnotationTwo.class)
printAnnotation(myAnnotationTwo)

MyAnnotationThree myAnnotationThree = SomeClassOne.getAnnotation(MyAnnotationThree.class)
printAnnotation(myAnnotationThree)







