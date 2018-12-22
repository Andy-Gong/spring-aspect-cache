# spring-aspect-cache

This project introduces how to enable the guava cache via @Cacheable during call one method. For example, if you want to cache the result of QueryService.getName(String key), what do you ONLY need to that adding a @Cacheable on the method.
What scenarios @Cacheable can be used to? 
1. You want to speed some memory to impprove speed.
2. You expect that keys sometimes will get queried more that once.

@Cacheable has 3 fields:
1. name: the name of cache instance, it is required, you can define one or more instances in your application,
2. duration: default value is 60, the duration time of the value in the cache instance,
3. timeUnit: default value is TimeUnit.SECONDS, the unit of duration time, the value can be TimeUnit.MILLISECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS.

Code example, the cache instance name is 'getName', and the duration time is 30m:
```
    @Cacheable(name = "getName", duration = 30, timeUnit = TimeUnit.MINUTES)
    public String getName(String key) {
        System.out.println("Receive key: " + key);
        return "123456";
    }
```

# Percondition
- Maven 3++
- java 8


# Run application

1. Download source code

```
git clone https://github.com/Andy-Gong/kafka-metrics.git
```

2. Run with source code

   Find SpringAspectGuavaCacheApplication class and run it.
