package org.eor.designPattern.proxy
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * JDK Proxy 是 jdk1.3 出的，具有历史局限性，当时的代理模式很多都是基于接口开发的。
 * 因此原生 Proxy 可以理解为将其转译为对等的基于接口开发的代理模式，
 * 本质是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用 InvokeHandler 来处理，而这个共同的接口就是 InvokeHandler。
 * 这也是为什么所有动态生成的代理类都调用的是 invoke 方法。
 *
 * 基于接口开发的代理模式，是指代理类跟实现类都实现同一个接口，然后在使用时将实现类委托给代理类来调用。
 * 比如 new 的时候是这样的：
 * IUserController userController = new UserControllerProxy(new UserController());
 *
 * 使用 JDK Proxy 的步骤：
 * 1. 实现 InvocationHandler 接口
 * 2. 编写 invoke 和获取 proxy 实例的代码
 * 3. 被代理对象需要继承自接口
 *
 * Groovy 本身就是用了 JDK 的代理实现的，因此在 Groovy 实现 JDK 代理时会出现奇怪现象，其本质是 Groovy 拿我们的对象也进行了一大堆的代理操作
 * 同时，代理模式的应用过于广泛，debug 时候，idea 会调用“被代理类”的 toString() 方法，这也会导致 invoke 被调用多次，所以推荐判断 method.getName() 的类型之后在处理
 * 注意 Proxy 是基于接口实现的，因此在赋值时要使用接口作为类型，而不是使用实现类，否则会报类型转换错误。
 * 注意1：对于从Object中继承的方法，JDK Proxy会把hashCode()、equals()、toString()这三个非接口方法转发给InvocationHandler，其余的Object方法则不会转发。详见JDK Proxy官方文档。
 */

interface Origin {
    public String doSomething()
}

class OriginImpl implements Origin {
    public String doSomething() {
        String str = "doSomething";
        println(str);
        return str;
    }
}

class MyLogAroundProxy<T> implements InvocationHandler {

    private T target;

    public MyLogAroundProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        println "当前方法名为：" + method.getName()
        doSomethingBefore();
        Object res = method.invoke(this.target, args);
        doSomethingAfter();
        return res;
    }
    // 根据思想的不同，
    // 一种写法是允许使用从外部传入的 被代理对象
    // 一种写法是不允许使用外部传入的 被代理对象
    public static <T> T getProxyInstance(T target) {
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MyLogAroundProxy(target))
        return (T) o;
    }

    public void doSomethingBefore() {
        println "doSomethingBefore"
    }

    public void doSomethingAfter() {
        println "doSomethingAfter"
    }
}

Origin origin = new OriginImpl()
Origin proxy = MyLogAroundProxy.getProxyInstance(origin)
// 下面的东西 Groovy 本身就会进行代理，因此在 Groovy 中 invoke 会被调用多次，其本质是 Groovy 自己就把这个函数调用了多次......
proxy.doSomething()


println("done")