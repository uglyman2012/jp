package com.xy.jp.bean;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/09/04
 */
public class Student {
    private Integer age;
    private String name;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
