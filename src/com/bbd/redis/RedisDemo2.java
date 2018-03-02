package com.bbd.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
//        method03();
//        method04();
//        method05();
//        method06();
//        method07();
//        method08();
        method09();
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
        jedis.rpoplpush("list2", "list1"); // 弹出list2列表中最右端的元素，然后推入的list1列表中的最左端
        System.out.println(jedis.lrange("list1", 0, -1));
        System.out.println(jedis.lrange("list2", 0, -1));
        jedis.brpoplpush("list2", "list1", 9); // 这个方法会阻塞9秒，当list2中有数据时，就会执行，当到时间时，直接返回一个null
    }

    public static void method04() {
        jedis.sadd("jihe", new  String[]{"1", "2", "3", "4"});
        logger.info(jedis.smembers("jihe").toString()); // 返回集合中的所有元素
        logger.info(jedis.scard("jihe").toString()); // 返回集合中元素的个数
        logger.info(jedis.srandmember("jihe")); // 随机返回集合中的一个元素
        logger.info(jedis.srandmember("jihe", 2).toString()); // 随机返回集合中的两个不重复元素
    }

    public static void method05() {
        jedis.sadd("jihe1", new String[]{"1", "2", "3", "4", "6"});
        jedis.sadd("jihe2", new String[]{"1", "2", "3", "4", "5"});
        jedis.sadd("jihe3", new String[]{"3", "4", "5", "7"});
        Set<String> rs = jedis.sdiff("jihe1", "jihe2", "jihe3"); // 返回存在于jihe1中，但不存在于后面其他集合中的数据（差）
        logger.info(rs.toString()); // [6]
    }

    public static void method06() {
        Map<String, Double> map1 = new HashMap<>();
        map1.put("a", 1.0);map1.put("b", 0.5);map1.put("c", 0.98);map1.put("d", 1.22);
        jedis.zadd("zset1", map1);

        Map<String, Double> map2 = new HashMap<>();
        map2.put("c", 1.98);map2.put("f", 0.24);
        jedis.zadd("zset2", map2);

        Long resp = jedis.zinterstore("jiaoji", new ZParams().aggregate(ZParams.Aggregate.SUM), "zset1", "zset2");
        System.out.println(resp);
        Set<Tuple> s = jedis.zrangeWithScores("jiaoji", 0, -1);
        for (Tuple tuple : s) {
            System.out.println(tuple.getElement() + " : " + tuple.getScore());
        }
    }

    public static void method07() {
        jedis.lpush("key11", new String[]{"3", "1", "2"});
        logger.info(jedis.lrange("key11", 0, -1).toString());
        List<String> resp = jedis.sort("key11", new SortingParams().desc()); // 取出结果，并对结果进行排序
        logger.info(resp.toString());
    }

    public static void method08() {
        jedis.lpush("key12", new String[]{"a", "c", "b", "f"});
        logger.info(jedis.lrange("key12", 0, -1).toString());
        List<String> resp = jedis.sort("key12", new SortingParams().alpha().desc()); // 取出结果，并对结果进行排序（对于非数字的排序，需要加alpha参数，否则会报转换成数据异常）
        logger.info(resp.toString());
    }

    public static void method09() {
        jedis.lpush("sort-input", new String[]{"a", "c", "b", "f"});
        jedis.hset("d-a", "field", "5");
        jedis.hset("d-b", "field", "1");
        jedis.hset("d-c", "field", "9");
        jedis.hset("d-f", "field", "3");
        // 将散列的权重，用来排序sort-input
        List<String> resp = jedis.sort("sort-input", new SortingParams().by("d-*->field"));
        System.out.println(resp);
    }

}
    
    