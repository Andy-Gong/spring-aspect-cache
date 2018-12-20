package com.example.demo.service;

import com.aspect.cache.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class QueryService {

    @Cacheable(name = "getName")
    public String getName(String key) {
        System.out.println("Receive key: " + key);
        return "123456";
    }
}
