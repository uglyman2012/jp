package com.xy.jp.service.rabbitmqDemo.demo1;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/23
 */
@Configuration
public class Receiver {
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(new Queue("hello"));
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        // 设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("1  receive msg : " + JSONObject.parseObject(new String(body)));
                //不读取消息并且将当前消息抛弃掉，消息队列中删除当前消息
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                //不读取消息，消息队列中保留当前消息未被查看状态
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);

                //确认消息成功消费，删除消息队列中的消息
                // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //确认消息成功消费，删除消息队列中的消息，true:将一次性ack所有小于deliveryTag的消息。
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer2(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(new Queue("hello"));
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        // 设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("  2  receive msg : " + JSONObject.parseObject(new String(body)));
                //不读取消息并且将当前消息抛弃掉，消息队列中删除当前消息
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                //不读取消息，消息队列中保留当前消息未被查看状态
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);

                //确认消息成功消费，删除消息队列中的消息
                // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //确认消息成功消费，删除消息队列中的消息，他跟上面貌似一样
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
        });
        return container;
    }
}
