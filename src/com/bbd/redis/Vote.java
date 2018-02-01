package com.bbd.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import sun.awt.image.codec.JPEGImageEncoderImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Liuweibo
 * @version Id: Vote.java, v0.1 2018/1/30 Liuweibo Exp $$
 */
public class Vote {

    private static final Double FACTOR = 432.0;

    private static final Double MILLILS = 7 * 86400.0;

    private static final String PUBLISH_TIME = "publish_time";

    private static final String SCORE = "score";

    private static final String ARTICLE = "article";

    private static final String USER ="user";

    private static final String COLON = ":";

    private static final String VOTED = "voted";

    private static final int MINUTE = 60;


    private static Jedis jedis;

    static {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    public static void main(String[] args) {
//        storageArticle();
//        storagePubtime();
//        storageScore();
//        dianzan("1025", "92617");
//        System.out.println(generateArticleId());
        juhe();
    }
    // 存储文章基本信息（用redis的散列存储类型来存储文章基本信息）
    public static void storageArticle() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "Go to statement considered harmful");
        map.put("link", "http://goo.gl/kZUSu");
        map.put("poster", USER + COLON + "83271");
        map.put("time", "1331382669.33");
        map.put("votes", "528");
        jedis.hmset(ARTICLE + COLON + "92617", map);
    }

    // 存放文章的发布时时间，用有序列表zset来存储
    public static void storagePubtime() {
        Map<String, Double> map = new HashMap<>();
        map.put(ARTICLE + COLON + "92617", 1531382669.33);
        map.put(ARTICLE + COLON + "92618", 1531382609.73);
        jedis.zadd(PUBLISH_TIME, map);
    }

    // 存放文章的得分，用有序列表来存放
    public static void storageScore() {
        Map<String, Double> map = new HashMap<>();
        map.put(ARTICLE + COLON + "92617", 528.0*FACTOR);
        map.put(ARTICLE + COLON + "92618", 228.0*FACTOR);
        jedis.zadd(SCORE, map);
    }

    // 发布一篇文章
    public static void pubArticle(Map<String, String> hash, String uid) {
        // 随机生成文章id
        int artId = generateArticleId();
        // 将该文章的用户id加入该文章的已投票用户名单里面（自己不能给自己投票）
        jedis.sadd(VOTED + COLON + artId, hash.get("poster"));
        // 设置名单过期时间为一分钟
        jedis.expire(VOTED + COLON + artId, MINUTE);
        // 将文章的基本信息存放在散列里面
        jedis.hmset(ARTICLE + COLON + artId, hash);
        // 将文章的发布时间和初始得分加入到两个有序集合里面
        jedis.zadd(PUBLISH_TIME, Double.valueOf(hash.get("time")), ARTICLE + COLON + artId);
        jedis.zadd(SCORE, FACTOR, ARTICLE + COLON + artId);
    }

    // 随机生成文章ID
    public static int generateArticleId() {
        int v = (int) (Math.random() * 100000);
        return v;
    }


    // 给某个文章点赞
    public static void dianzan(String uid, String articleId) {
        // step-1：计算该文章截止点赞日期
        long now = System.currentTimeMillis()/1000;
        double cutoff = now - MILLILS;
        Double time = jedis.zscore(PUBLISH_TIME, ARTICLE + COLON + articleId);
        if (time < cutoff) throw new RuntimeException("该文章已经不能点赞了");

        // step-2：判断该用户是否是第一次给改文章点赞
        Boolean isFirst = !jedis.sismember(VOTED + COLON + articleId, USER + COLON + uid);

        if (isFirst) {
            // step-3：该文章的点赞用户id存储起来
            jedis.sadd(VOTED + COLON + articleId, USER + COLON + uid);
            // step-4: 得分增加(+432)
            jedis.zincrby(SCORE, FACTOR, ARTICLE + COLON + articleId);
            // step-5：得票数增加
            jedis.hincrBy(ARTICLE + COLON + "92617", "votes", 1);
        }
    }

    // redis聚合操作
    public static void juhe() {
        jedis.sadd("group:java", ARTICLE + COLON + 10001, ARTICLE + COLON + 10002, ARTICLE + COLON + 10003, ARTICLE + COLON + 10004);
        Map<String, Double> map = new HashMap<>();
        map.put(ARTICLE + COLON + 10001, 10.25);
        map.put(ARTICLE + COLON + 10004, 1.67);
        map.put(ARTICLE + COLON + 10007, 5.02);
        jedis.zadd(SCORE, map);
        jedis.zinterstore("result", "group:java", SCORE);
        Set<Tuple> rs = jedis.zrangeWithScores("result", 0, -1);
        for (Tuple t : rs) {
            System.out.println(t.getElement() + " ; " + t.getScore());
        }
    }
}

















    