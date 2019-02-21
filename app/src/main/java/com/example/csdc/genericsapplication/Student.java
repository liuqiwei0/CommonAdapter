package com.example.csdc.genericsapplication;

/**
 * Created by csdc on 2019/2/19.
 */

public class Student implements ListDataModel{

    private int age;

    private String name;

    private String className;

    public Student(int age, String name, String className) {
        this.age = age;
        this.name = name;
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
