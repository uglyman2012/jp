package com.xy.jp.service.rabbitmqDemo.demo2;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */
@Component
public class RemakeNameMessageListener {

    private static final Logger log = LoggerFactory.getLogger(RemakeNameMessageListener.class);


    @RabbitListener(queues = AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_QUEUE)
    public void remakeNameQueueListener(Message message, Channel channel) throws IOException {
        try {
            RabbitFriend rabbitFriend = RabbitUtils.buildMessage(message, RabbitFriend.class);
            log.info("RemakeNameMessageListener|remakeNameQueueListener,correlationDataId:{},RabbitFriend:{}", message.getMessageProperties().getHeaders().get("spring_listener_return_correlation"), rabbitFriend);

            //do you things


            log.info("RemakeNameMessageListener|remakeNameQueueListener，Consumption of success");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("RemakeNameMessageListener|remakeNameQueueListener，Consumption of failed,cause:{}", e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }


}
