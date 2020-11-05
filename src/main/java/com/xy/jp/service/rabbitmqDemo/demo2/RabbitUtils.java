package com.xy.jp.service.rabbitmqDemo.demo2;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */

public class RabbitUtils {

    private static final Logger logger = LoggerFactory.getLogger(RabbitUtils.class);


    public static void sendModifyRemakeNameMsg(RabbitTemplate rabbitTemplate, RabbitFriend rabbitFriend){
        try{
            if(rabbitFriend.getLists().size() < 1){
                return;
            }
            logger.info("sendModifyRemakeNameMsg request:"+JSON.toJSONString(rabbitFriend));
            CorrelationData correlationDataId = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(
                    AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_DIRECT_EXCHANGE,
                    AmqpConstant.MODIFY_FRIEND_REMAKE_NAME_ROUTING_KEY,
                    rabbitFriend,
                    correlationDataId
            );
        }catch (Exception e){
            logger.info("mq消息发送错误",e);
        }
    }

    public static RabbitFriend buildMessage(Message message, Class<RabbitFriend> rabbitFriendClass) {
        String data = (String) JSON.parse(message.getBody());
        RabbitFriend rabbitFriend = JSON.parseObject(data, rabbitFriendClass);
        return rabbitFriend;
    }
}
