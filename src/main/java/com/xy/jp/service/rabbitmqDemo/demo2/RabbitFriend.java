package com.xy.jp.service.rabbitmqDemo.demo2;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */
public class RabbitFriend implements Serializable {
    private int userId;
    private List<RabbitNickName> lists;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<RabbitNickName> getLists() {
        return lists;
    }

    public void setLists(List<RabbitNickName> lists) {
        this.lists = lists;
    }
}
