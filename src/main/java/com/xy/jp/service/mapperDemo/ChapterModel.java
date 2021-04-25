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
public class ChapterModel {
    private Long id;
    private Long bookId;
    private Long parentId;
    private String groupName;
    private String parentName; // 省略 get/set
    private String fid;
    private Student student;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "ChapterModel{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", parentId=" + parentId +
                ", groupName='" + groupName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", fid='" + fid + '\'' +
                ", student=" + student +
                '}';
    }
}
