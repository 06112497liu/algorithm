package com.bbd.redis;

import redis.clients.jedis.Jedis;

/**
 * @author Liuweibo
 * @version Id: Vote.java, v0.1 2018/1/30 Liuweibo Exp $$
 */
public class Vote {

    private static final Double SCORE = 432.0;

    private static final Long MILLILS = 7 * 86400L;


    private static Jedis jedis;

    static {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    // 存储文章基本信息
    public static void storageArticle() {
        jedis.
    }

}
    
    