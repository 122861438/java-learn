package com.cx.springbootmybatis.entity;

public class Teacher {

    private Integer id;

    private String teacherName;

    private Integer teacherAge;

    public Teacher(){}

    public Teacher(String teacherName,Integer teacherAge){
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", teacherAge=" + teacherAge +
                '}';
    }
}
