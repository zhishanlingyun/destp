package com.destp.spring.bean.scan.dto;

import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
@Repository
public class FileDao {

    private String name = "fffffff";

    public void find(){
        System.out.println(this.name+"-FileDao find()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
