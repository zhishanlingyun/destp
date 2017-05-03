package com.destp.bean;

import jodd.vtor.constraint.MaxLength;
import jodd.vtor.constraint.NotBlank;

/**
 * Created by zsly on 17-5-3.
 */
public class User {

    private long id;

    @MaxLength(value = 3,message = "big 3")
    @NotBlank(message = "not null")
    private String name;

    private int age;

    public User() {
    }

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
