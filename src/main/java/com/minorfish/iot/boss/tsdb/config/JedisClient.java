package com.minorfish.iot.boss.tsdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisClient {

    @Autowired @Qualifier("jedis.pool") private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

}
