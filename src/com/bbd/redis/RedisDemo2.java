package com.bbd.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import javax.print.attribute.standard.MediaSize;

/**
 * @author Liuweibo
 * @version Id: RedisDemo2.java, v0.1 2018/2/5 Liuweibo Exp $$
 */
public class RedisDemo2 {
    private static Jedis jedis;

    private static final Logger logger = LoggerFactory.getLogger(RedisDemo2.class);

    static {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.select(2);
    }

    public static void main(String[] args) {
//        method01();
//        method02();
        method03();
    }

    public static void method01() {
        jedis.set("money", "5");
        logger.info("自增前：{}", jedis.get("money"));
        jedis.incr("money"); // 这是自增+1，当然也可以自减（decr）
        logger.info("自增1后：{}", jedis.get("money"));
        jedis.incrBy("money", 4L); // 加上一个整数
        logger.info("+4后：{}", jedis.get("money"));
        jedis.incrByFloat("money", 2.36D); // 加上一个浮点数
        logger.info("+2.36后：{}", jedis.get("money"));
    }

    public static void method02() {
        jedis.set("country", "china");
        logger.info(jedis.get("country"));
        jedis.append("country", "-niubi");
        logger.info(jedis.get("country"));
        logger.info(jedis.getrange("country", 0, 1));
        jedis.setrange("country", 1, "buniubi");
        logger.info(jedis.get("country"));
    }

    public static void method03() {
        jedis.rpush("list1", "item1");
        jedis.rpush("list1", "item2");
        jedis.rpush("list2", "ele1");
        System.out.println(jedis.lrange("list1", 0, -1));
        System.out.println(jedis.lrange("list2", 0, -1));
        jedis.rpoplpush("list2", "list1");
        System.out.println(jedis.lrange("list1", 0, -1));
        System.out.println(jedis.lrange("list2", 0, -1));
        jedis.brpoplpush("list2", "list1", 9); // 这个方法会阻塞9秒，当list2中有数据时，就会执行，当到时间是，直接返回一个null
    }

}
    
    