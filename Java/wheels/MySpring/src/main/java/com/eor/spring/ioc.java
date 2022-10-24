package com.eor.spring;

public class ioc {

//    public static <T> T registerBean(ConfigurableApplicationContext applicationContext,
//                                     String name,
//                                     Class<T> clazz,
//                                     Object... args) {
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
//        for (Object arg : args) {
//            beanDefinitionBuilder.addConstructorArgValue(arg);
//        }
//        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
//        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
//        beanFactory.registerBeanDefinition(name, beanDefinition);
//        return (T) applicationContext.getBean(name, clazz);
//    }
}
