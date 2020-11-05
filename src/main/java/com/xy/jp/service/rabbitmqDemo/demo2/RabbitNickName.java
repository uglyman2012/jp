package com.xy.jp.service.rabbitmqDemo.demo2;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */
public class RabbitNickName implements Serializable {

    private String name;
    private String id;

    public RabbitNickName() {
    }

    public RabbitNickName(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
