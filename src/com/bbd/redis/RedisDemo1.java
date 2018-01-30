package com.bbd.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Map;
import java.util.Set;

/**
 * @author Liuweibo
 * @version Id: RedisDemo1.java, v0.1 2018/1/30 Liuweibo Exp $$
 */
public class RedisDemo1 {

    private static Jedis jedis;

    private static final Logger logger = LoggerFactory.getLogger("com.bbd");

    static {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    public static void main(String[] args) {
//        m01();
//        m02();
//        m03();
//        m04();
        m05();
    }

    public static void m01() {
        jedis.set("key1", "value1");
        System.out.println(jedis.get("key1"));
    }

    public static void m02() {
        jedis.lpush("list_key", new String[]{"1", "2", "3"});
        // lpop，取出（列表中这个元素就移除了）列表中左边第一个元素
        logger.info(jedis.lpop("list_key")); // 3
    }

    public static void m03() {
        jedis.sadd("set", new String[]{"set1", "set2", "set3", "set2"});
        Set<String> sets = jedis.smembers("set");
        // 判断键为set的的集合是否包含元素set2
        Boolean isContain = jedis.sismember("set", "set2");
        logger.info(isContain.toString());
        logger.info(sets.toString());
        // 移除元素，并返回移除的数量
        Long element = jedis.srem("set", "set2");
        logger.info(element.toString());
    }

    public static void m04() {
        jedis.hset("hash_key", "name", "xiaoming");
        jedis.hset("hash_key", "age", "23");
        String name = jedis.hget("hash_key", "name");
        logger.info(name); // xiaoming
        Map<String, String> map = jedis.hgetAll("hash_key");
        System.out.println(map);
    }
    public static void m05() {
        jedis.zadd("zset", 20, "element1");
        jedis.zadd("zset", 25, "element2");
        jedis.zadd("zset", 21, "element3");
        Set<String> sets = jedis.zrangeByScore("zset", 0, 24);
        logger.info(sets.toString());
        Set<Tuple> sets2 = jedis.zrangeWithScores("zset", 0, -1);
        for (Tuple tuple : sets2) {
            System.out.println(tuple.getElement() + " : " + tuple.getScore());
        }
    }
}
    
    