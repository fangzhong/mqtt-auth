package com.minorfish.iot.boss.tsdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
//@PropertySource(value = "classpath:redis.properties")
public class RedisCacheConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.database}")
    private int database = 0;

    @Value("${redis.pool.maxTotal}")
    private int maxTotal = 8;                                       //maxActive -> maxTotal
    @Value("${redis.pool.maxIdle}")
    private int maxIdle = 8;
    @Value("${redis.pool.minIdle}")
    private int minIdle = 0;
    @Value("${redis.pool.maxWaitMillis}")
    private int maxWaitMillis = -1;                                 //maxWait -> maxWaitMillis
    @Value("${redis.pool.testOnReturn}")
    private boolean testOnReturn = false;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow = false;
    @Value("${redis.pool.testWhileIdle}")
    private boolean testWhileIdle = false;
    @Value("${redis.pool.testOnCreate}")
    private boolean testOnCreate = false;
    @Value("${redis.pool.blockWhenExhausted}")
    private boolean blockWhenExhausted = true;
    @Value("${redis.pool.lifo}")
    private boolean lifo = true;                                        //是否启用后进先出，默认true

    //------------------jmx管理功能
    @Value("${redis.pool.jmxEnabled}")
    private boolean jmxEnabled = true;                                  //是否启用pool的jmx管理功能，默认true
    @Value("${redis.pool.jmxNamePrefix}")
    private String jmxNamePrefix = "pool";                              //
    @Value("${redis.pool.jmxNameBase}")
    private String jmxNameBase;

    @Value("${redis.pool.evictionPolicyClassName}")
    private String evictionPolicyClassName = "org.apache.commons.pool2.impl.DefaultEvictionPolicy";                             //逐出策略类名
    @Value("${redis.pool.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis = 1800000;                   //逐出连接的最小空闲时间
    @Value("${redis.pool.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun = 3;                             //每次逐出检查时，逐出的最大数目，如果为负数就是：1/abs(n)，默认3
    @Value("${redis.pool.softMinEvictableIdleTimeMillis}")
    private int softMinEvictableIdleTimeMillis = 1800000;               //对象空闲多久后逐出，当空闲时间 > 该值 且 空闲连接 > 最大空闲数时直接逐出，不再根据MinEvictableIdleTimeMillis判断
    @Value("${redis.pool.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis = -1;                     //逐出扫描的时间间隔（毫秒）,如果为负数，则不运行逐出线程，默认 -1

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setLifo(this.lifo);

        //jmx设置
        config.setJmxEnabled(this.jmxEnabled);
        config.setJmxNamePrefix(this.jmxNamePrefix);
//        config.setJmxNameBase();

        //常用设置
        config.setMaxTotal(this.maxTotal);
        config.setMaxIdle(this.maxIdle);
        config.setMinIdle(this.minIdle);
        config.setMaxWaitMillis(this.maxWaitMillis);
        config.setTestOnReturn(this.testOnReturn);
        config.setTestOnBorrow(this.testOnBorrow);
        config.setTestWhileIdle(this.testWhileIdle);
        config.setTestOnCreate(this.testOnCreate);
        config.setBlockWhenExhausted(this.blockWhenExhausted);

        //逐出策略及其参数设置
        config.setEvictionPolicyClassName(this.evictionPolicyClassName);
        config.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        config.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
        config.setSoftMinEvictableIdleTimeMillis(this.softMinEvictableIdleTimeMillis);
        config.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);

        return config;
    }

    @Bean(name = "jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config) {
        return new JedisPool(config, this.host, this.port, this.timeout, this.password, this.database);
    }
}
