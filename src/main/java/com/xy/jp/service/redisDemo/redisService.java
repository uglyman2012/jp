package com.xy.jp.service.redisDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/22
 */
@Service
public class redisService {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    public void redisTest1(){
        redisTemplate.opsForValue().set("22","pppoii");
    }
}
