package com.aspect.cache;

import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class CacheAspect {

    @Pointcut("@annotation(com.aspect.cache.Cacheable)")
    public void cacheAspect() {
    }

    @Around(value = "cacheAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Annotation annotation = method.getAnnotation(Cacheable.class);
        if (annotation != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(method.getDeclaringClass().getName());
            stringBuffer.append('_');
            stringBuffer.append(method.getName());
            stringBuffer.append('_');
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length != 0) {
                for (Object arg : args) {
                    stringBuffer.append(arg.toString());
                }
            }
            String key = stringBuffer.toString();
            String cacheName = ((Cacheable) annotation).name();
            if (LocalCaches.getCache(cacheName) == null) {
                LocalCaches.addCache(cacheName, CacheBuilder.newBuilder().expireAfterWrite(((Cacheable) annotation).duration(), ((Cacheable) annotation).timeUnit()).build());
            }
            Object value = LocalCaches.getCache(cacheName).getIfPresent(key);
            if (value == null) {
                value = joinPoint.proceed();
                LocalCaches.getCache(cacheName).put(key, value);
                return value;
            } else {
                return value;
            }
        }
        return joinPoint.proceed();
    }
}
