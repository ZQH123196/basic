package org.eor.designPattern.annotation.retention

import java.lang.annotation.Annotation
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnotationOne {
    String when() default "before";
    String value() default "default value";
}

@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotationTwo {
    String when() default "before";
    String value() default "default value";
}

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotationThree {
    String when() default "before";
    String value() default "default value";
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






