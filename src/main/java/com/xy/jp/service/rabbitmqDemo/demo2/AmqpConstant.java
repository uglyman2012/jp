package com.xy.jp.service.rabbitmqDemo.demo2;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */
public interface AmqpConstant {

    /**
     * 修改好友备注交换机
     */
    String MODIFY_FRIEND_REMAKE_NAME_DIRECT_EXCHANGE = "modifyFriendRemakeNameDirectExchange";

    /**
     * 修改好友备注队列
     */
    String MODIFY_FRIEND_REMAKE_NAME_QUEUE = "modifyFriendRemakeNameQueue";

    /**
     * 修改好友备注路由键
     */
    String MODIFY_FRIEND_REMAKE_NAME_ROUTING_KEY = "modifyFriendRemakeNameRoutingKey";


}
