package spring;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class SpringTest {

    /**
     * 场景：检查一个类是否被载入当前类加载器的上下文
     * 好处是内部有很多判断，错误返回人性化一点，而且有缓存，工具类的性能会高很多
     * 可以实现类似于 @ConditionOnClass 当某个类存在的时候在进行相对逻辑
     */
    @Test
    public void classUtil() {

        String className = "abc.eor.bbb";

        // 常规检查 类 是否被加载的方法
//        try {
////            Class.forName(className);
//            getClass().getClassLoader().loadClass(className);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // 加载不存在的类
        boolean present = ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
        System.out.println(present);

        // 加载当前类，这个肯定为 true
        boolean present2 = ClassUtils.isPresent(getClass().getName(), ClassUtils.getDefaultClassLoader());
        System.out.println(present2);
    }
}
