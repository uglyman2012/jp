package com.xy.jp.controller;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/09/17
 */
public class BBB implements Cloneable{
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BBB bbb=(BBB)super.clone();
        return bbb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
