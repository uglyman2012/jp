package com.xy.jp.service.rabbitmqDemo.DirectDemo3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/11/04
 */
@Component
public class RabbitCallback implements RabbitTemplate.ReturnCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitCallback.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
         //也可以通过这种方式配置
         //rabbitTemplate.setMandatory(true);

        // 每个RabbitTemplate只能设置一个RabbitTemplate.ReturnCallback
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 交换机路由到队列失败才会回调
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.warn("return callback, receive message ：" + message.toString() + ", " + replyText + ", " + exchange + ", " + routingKey);
    }
}
