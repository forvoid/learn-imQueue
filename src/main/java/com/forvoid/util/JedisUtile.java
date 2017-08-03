package com.forvoid.util;


import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by forvoid on 2017/8/2.
 */
public class JedisUtile {
    public static Logger log = LoggerFactory.getLogger(JedisUtile.class);
    private static JedisPool jedisPool = null;

    static {
        //从属性文件读取配置
//        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        String ip = "127.0.0.1";
        String port ="6379";
        int timeout =2000;
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(50);
            config.setMaxWaitMillis(5000);
            config.setTestOnBorrow(true);
            // 在还回给pool时，是否提前进行validate操作
            config.setTestOnReturn(true);
            //连接池设置
            jedisPool =  new JedisPool(config,ip);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void put(String key, String value) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 还回到连接池
            returnResource(jedis);
        }
    }

    public static String get(String key) {
        String value = "";
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 还回到连接池
            returnResource(jedis);
        }
        return value;
    }

    public static boolean exists(String key) {
        boolean bool = false;
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = getJedis();
            bool = jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 还回到连接池
            returnResource(jedis);
        }
        return bool;
    }

//    在set中追加数据
    public static void incr(String key) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = getJedis();
            jedis.incr(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 还回到连接池
            returnResource(jedis);
        }
    }

    public static void sadd(String key, String members) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = getJedis();
            jedis.sadd(key, members);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 还回到连接池
            returnResource(jedis);
        }
    }

    public static boolean sismember(String key, String members) {
        Jedis jedis = null;
        boolean bool = false;
        // 从池中获取一个jedis实例
        try {
            jedis = getJedis();
            bool = jedis.sismember(key, members);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 还回到连接池
            returnResource(jedis);
        }
        return bool;
    }
}
