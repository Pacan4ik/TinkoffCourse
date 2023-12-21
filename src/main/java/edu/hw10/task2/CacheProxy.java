package edu.hw10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private Map<String, Object> cache;

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new HashMap<>();
    }

    public static <T> T create(T cacheableObj, Class<?> tClass) {
        return (T) Proxy.newProxyInstance(
            tClass.getClassLoader(),
            tClass.getInterfaces(),
            new CacheProxy(cacheableObj)
        );
    }

    public static <T> T create(T cacheableObj, Class<?> tClass, String existedCache) {
        CacheProxy cacheProxy = new CacheProxy(cacheableObj);
        cacheProxy.loadCacheFromPath(existedCache);
        return (T) Proxy.newProxyInstance(
            tClass.getClassLoader(),
            tClass.getInterfaces(),
            cacheProxy
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        }

        String key = String.format("%s(%s)", method.getName(), Arrays.toString(args));
        Object cacheValue = cache.get(key);
        if (!Objects.isNull(cacheValue)) {
            return cacheValue;
        }
        cacheValue = method.invoke(target, args);
        cache.put(key, cacheValue);
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation.persist()) {
            saveCache(cacheAnnotation.path());
        }
        return cacheValue;
    }

    private void saveCache(String path) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(cache);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCacheFromPath(String path) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            Object map = inputStream.readObject();
            if (map instanceof Map) {
                cache = new HashMap<>((Map<String, Object>) map);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
