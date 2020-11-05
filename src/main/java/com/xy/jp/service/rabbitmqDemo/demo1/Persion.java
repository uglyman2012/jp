package com.xy.jp.service.rabbitmqDemo.demo1;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/23
 */
public class Persion {
    private String name;
    private java.util.Date today;

    public Persion(String name, Date today) {
        this.name = name;
        this.today = today;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
