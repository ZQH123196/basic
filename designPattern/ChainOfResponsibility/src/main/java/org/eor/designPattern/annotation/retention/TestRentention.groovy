package org.eor.designPattern.annotation.retention

import java.lang.annotation.Annotation
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * 体现 @Retention 对注解存活期的限定
 */

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationOne {
    String when() default "MyAnnotationOne";

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

@MyAnnotationOne
@MyAnnotationTwo
@MyAnnotationThree
class SomeClassOne {

}

public void printAnnotation(Annotation annotation) {
    if (annotation != null) {
        println("${annotation}")
    }
}

// 编译出来的 class 就不存在该注解，主要给编译器使用
MyAnnotationOne myAnnotationOne = SomeClassOne.getAnnotation(MyAnnotationOne.class)
printAnnotation(myAnnotationOne)

// 编译出来的 class 存在，但是 JVM 不加载，运行时不存在，主要给三方库使用
MyAnnotationTwo myAnnotationTwo = SomeClassOne.getAnnotation(MyAnnotationTwo.class)
printAnnotation(myAnnotationTwo)

// JVM 运行时存在
MyAnnotationThree myAnnotationThree = SomeClassOne.getAnnotation(MyAnnotationThree.class)
printAnnotation(myAnnotationThree)






