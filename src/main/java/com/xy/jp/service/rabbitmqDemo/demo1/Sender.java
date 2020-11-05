package com.xy.jp.service.rabbitmqDemo.demo1;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/23
 */
@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i <20 ; i++) {
            this.rabbitTemplate.convertAndSend ("hello", new Persion("zhangsn"+i,new Date()));

        }
    }
}
