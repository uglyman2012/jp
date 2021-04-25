package com.xy.jp.service.mapperDemo;

import com.xy.jp.bean.Student;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/11/10
 */

public class Chapter {

    private Long id;
    private String name;
    private Long parentId;
    private String description;
    private Integer sortNum;
    private Student student;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                ", sortNum=" + sortNum +
                ", student=" + student +
                '}';
    }
}
