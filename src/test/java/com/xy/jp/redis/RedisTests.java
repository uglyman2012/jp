package com.xy.jp.redis;

import com.xy.jp.bean.Student;
import com.xy.jp.service.redisDemo.redisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private redisService redisService;

    @Test
    void test1() {
        Student student = new Student();
        student.setName("pppp");
        student.setAge(23);
        redisTemplate.opsForValue().set("11233", student);
        Object o = redisTemplate.opsForValue().get("11233");
        System.out.println(o.toString());
    }

    @Test
    void test5() {
        redisService.redisTest1();
    }

    @Test
    void test2() {
        Object o = redisTemplate.opsForValue().get("11");
        System.out.println(o.toString());
    }

    @Test
    void test3() {
        Student student = new Student();
        student.setName("pppp");
        student.setAge(23);
        //redisTemplate.opsForHash().put("hashValue","map1","map1-1");
        //redisTemplate.opsForHash().put("hashValue","map2","map2-2");
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        redisTemplate.opsForHash().put("hashValue2", "map1", 2);
        //Map<String,Object> map = redisTemplate.opsForHash().entries("hashValue");
        //System.out.println("通过entries(H key)方法获取变量中的键值对:" + map);
        double hashIncDouble = redisTemplate.opsForHash().increment("hashValue2", "map1", 3);
        //Map<String,Object> map = redisTemplate.opsForHash().entries("hashValue");
        //System.out.println("通过values(H key)方法获取变量中的hashMap值:" + map);
        System.out.println("通过increment(H key, HK hashKey, double delta)方法使变量中的键以值的大小进行自增长:" + hashIncDouble);
    }
}
