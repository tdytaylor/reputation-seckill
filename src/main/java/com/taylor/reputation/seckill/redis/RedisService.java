package com.taylor.reputation.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisConfig config;

    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        return JSON.toJSONString(value);
    }

    private <T> T stringToBean(String str) {
        return null;
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(config.getPoolMaxIdle());
        poolConfig.setMaxTotal(config.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(config.getPoolMaxWait());
        JedisPool jedisPool = new JedisPool(poolConfig, config.getHost(), config.getPort(),
                config.getTimeout() * 1000, config.getPassword(), 0);
        return jedisPool;
    }
}
