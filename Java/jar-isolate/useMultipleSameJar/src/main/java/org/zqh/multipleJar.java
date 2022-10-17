package org.zqh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 1. 简单的自定义加载
 * 2. 带上下文环境的影响，摒除上层 ClassLoader 影响
 */

/**
 * 实现隔离允许指定 jar 包，主要需要做到以下几点：
 * <p>
 * 自定义 ClassLoader，使其 Parent = null，避免其使用系统自带的 ClassLoader 加载 Class。
 * 在调用相应版本的方法前，更改当前线程的 ContextClassLoader，避免扩展包的依赖包通过Thread.currentThread().getContextClassLoader()获取到非自定义的 ClassLoader 进行类加载
 * 通过反射获取 Method 时，如果参数为自定义的类型，一定要使用自定义的 ClassLoader 加载参数获取 Class，然后在获取 Method，同时参数也必须转化为使用自定义的 ClassLoade 加载的类型（不同 ClassLoader 加载的同一个类不相等）
 * （1）、根类加载器(Bootstrap) --C++写的，看不到源码
 * （2）、扩展类加载器（Extension） --加载位置 ：jre\lib\ext 中
 * （3）、系统(应用)类加载器(System\App)     --加载位置 ：classpath 中
 * （4）、自定义加载器(必须继承 ClassLoader)
 *
 * 类加载有三种方式：
 * 1、命令行启动应用时候由JVM初始化加载
 * 2、通过Class.forName()方法动态加载
 * 3、通过ClassLoader.loadClass()方法动态加载

 */
public class multipleJar {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

        // 临时更改 ClassLoader
        Thread.currentThread().setContextClassLoader(new URLClassLoader(new URL[]{}, null));

        loadMultipleJar();
        // 还原为之前的 ClassLoader
        Thread.currentThread().setContextClassLoader(oldClassLoader);
//        new Thread().setContextClassLoader();

//        当然因为引导类加载器通常是C/C++编写的 Java获取不到
//          所以通常得到的是个Null app 与 system 加载器是一个玩意
//        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(systemClassLoader);
//        ClassLoader extClassLoader = systemClassLoader.getParent();
//        System.out.println(extClassLoader);
//        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
//        System.out.println(bootstrapClassLoader);

        // 打印已经被 classload 加载的类，注意很多类都是懒加载的，懒加载的类不会被打印，只有被初始化过的会被打印
//        Field field = ClassLoader.class.getDeclaredField("classes");
//        for (Field declaredField : ClassLoader.class.getDeclaredFields()) {
//            System.out.println(declaredField);
//        }
//        System.out.println("--------------------");
//        Field field = systemClassLoader.getClass().getDeclaredField("classes");
//        field.setAccessible(true);
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        Vector<Class> classes =  (Vector<Class>) field.get(classLoader);
//        System.out.println("------------------");
//        Version.printVersion();
//        for (Class aClass : classes) {
//            System.out.println(aClass.getName());
//        }


    }


    public static void loadMultipleJar() throws MalformedURLException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String firstJarStr = "file:D:\\Code\\Project\\basic\\Java\\jar-isolate\\jar-first\\target\\jar-1.0.jar";
        String secondJarStr = "file:D:\\Code\\Project\\basic\\Java\\jar-isolate\\jar-second\\target\\jar-2.0.jar";
        URLClassLoader firstUrlClassLoader = new URLClassLoader(new URL[]{new URL(firstJarStr)},
                null);
        URLClassLoader secondUrlClassLoader = new URLClassLoader(new URL[]{new URL(secondJarStr)},
                null);


        String targetClassName = "org.example.jar.Version";
        String targetMethodName = "printVersion";

        Class firstClazz = Class.forName(targetClassName, true, firstUrlClassLoader);
        Method firstClazzMethod = firstClazz.getMethod(targetMethodName, null);
        String firstRes = (String) firstClazzMethod.invoke(firstClazz.newInstance());
        System.out.println(firstRes);

        Class secondClazz = Class.forName(targetClassName, true, secondUrlClassLoader);
        Method secondClazzMethod = secondClazz.getMethod(targetMethodName, null);
        String secondRes = (String) secondClazzMethod.invoke(secondClazz.newInstance());
        System.out.println(secondRes);
    }
}
