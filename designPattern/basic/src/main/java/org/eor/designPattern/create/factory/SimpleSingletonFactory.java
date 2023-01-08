package org.eor.designPattern.create.factory;

import org.eor.designPattern.create.factory.basic.ConfigParser;
import org.eor.designPattern.create.factory.basic.JsonParser;
import org.eor.designPattern.create.factory.basic.XmlParser;
import org.eor.designPattern.create.factory.basic.YamlParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 简单工厂实现二，缓存，单例
public class SimpleSingletonFactory {
    static final Map<String, ConfigParser> cache = new ConcurrentHashMap();

    static {
        cache.put("json", new JsonParser());
        cache.put("xml", new XmlParser());
        cache.put("yaml", new YamlParser());
    }

    public static ConfigParser getParser(String key) {
        return cache.get(key);
    }


}
