package com.destp.sharend.shop.frameworker.view;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public class Result {

    private boolean sucess;

    private String content;

    private String page;

    public Result(){}

    public Result(boolean sucess) {
        this.sucess = sucess;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
