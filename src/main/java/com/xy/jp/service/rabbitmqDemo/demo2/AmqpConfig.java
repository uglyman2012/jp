package com.xy.jp.service.rabbitmqDemo.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */
@Configuration
public class AmqpConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    //@Bean
    //public CachingConnectionFactory rabbitConnectionFactory() {
    //    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("10.68.244.130");
    //    connectionFactory.setUsername("test");
    //    connectionFactory.setPassword("123456");
    //    connectionFactory.setPort(5672);
    //    //connectionFactory.setVirtualHost("test"); //不能在yml中配置
    //    return connectionFactory;
    //}
    @Bean
    public AmqpTemplate amqpTemplate() {
        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setUsePublisherConnection(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });
        return rabbitTemplate;
    }

    /**
     * 修改好友备注交换机
     */
    @Bean(AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_DIRECT_EXCHANGE)
    public Exchange modifyFriendRemakeNameDirectExchange() {
        return ExchangeBuilder
                .directExchange(AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_DIRECT_EXCHANGE)
                .durable(true)
                .build();
    }

    /**
     * 队列
     */
    @Bean(AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_QUEUE)
    public Queue modifyFriendRemakeNameQueue() {
        return QueueBuilder.durable(AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_QUEUE).build();
    }

    /**
     * 队列 关联 路由key 关联 交换机
     *
     * @return
     */
    @Bean
    public Binding modifyFriendRemakeNameBinding(Queue modifyFriendRemakeNameQueue, Exchange modifyFriendRemakeNameDirectExchange) {
        return BindingBuilder
                .bind(modifyFriendRemakeNameQueue)
                .to(modifyFriendRemakeNameDirectExchange)
                .with(AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_ROUTING_KEY)
                .noargs();
    }


}
