package com.bbd.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.PipelineBlock;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Liuweibo
 * @version Id: RedisTransactionDemo.java, v0.1 2018/2/6 Liuweibo Exp $$
 */
public class RedisTransactionDemo {

    final static ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static Jedis jedis;

    private static final Logger logger = LoggerFactory.getLogger(RedisDemo2.class);

    static {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.select(3);
    }

    public static void main(String[] args) throws Exception {
//        m01();
//        m02();
//        m03();
        m04();
    }

    public static void m01() {
        jedis.set("key1", "0");
        for (int i = 0; i < 1000; i++) {
            Jedis jedis2 = new Jedis("127.0.0.1", 6379);
            jedis2.select(3);
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                Integer count = Integer.valueOf(jedis2.get("key1"));
                System.out.println(count);
                jedis2.set("key1", String.valueOf(count + 1)); // 这种操作可能存在线程安全的问题，这个分了两步，一步是取出数据+1，另一步是赋值操作
                jedis2.incr("key1"); // 这种就不会存在线程安全问题，因为这是原子操作。redis是单进程单线程的
            });
        }
        System.out.println(jedis.get("key1"));
    }

    /**
     * 存在安全问题，因为多线程执行，一个自增，一个自减的话，由于是两步多线程，可能导致错误
     */
    public static void m02() {
        jedis.set("key2", "0");
        for (int i = 0; i < 100; i++) {
            Jedis jedis2 = new Jedis("127.0.0.1", 6379);
            jedis2.select(3);
            executorService.execute(() -> {
                jedis2.incrBy("key2", 1); // +1操作
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jedis2.incrBy("key2", -1); // -1操作
            });
        }
        System.out.println(jedis.get("key2"));
    }

    /**
     * 使用事务，将客户端的命令都放到队列里面，调用exec()方法后，redis再去按顺序执行队列里面的命令
     */
    public static void m03() {
        jedis.set("key2", "0");
        for (int i = 0; i < 100; i++) {
            Jedis jedis2 = new Jedis("127.0.0.1", 6379);
            jedis2.select(3);
            executorService.execute(() -> {
                Transaction tran = jedis2.multi(); // 开启一个事物
                tran.incr("key2"); // +1操作
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tran.decr("key2"); // -1操作
                List<Object> list = tran.exec();
                System.out.println(list);
            });
        }
        System.out.println(jedis.get("key2"));
    }

    public static void m04() {
        jedis.set("key3", "0");
        Pipeline pipe = jedis.pipelined();
        String watch = jedis.watch("key3"); // watch表示监视key3这个键，然后在exec()方法之前，如果这个键被其他客户端修改或者删除，事务将不会被执行。exec()返回空集合
        Transaction tran = jedis.multi();
        tran.incrBy("key3", 5);
        tran.incrBy("key3", -2);
        //String h = tran.discard(); // 清空事务队列，取消事务（返回ok），下面的exec()会报错。ERREXEC without MULTI，因为先取消事务了，就没有multi()方法了
        List<Object> resp = tran.exec();
        for (Object o : resp) {
            System.out.println(o);
        }
    }

}


    
    