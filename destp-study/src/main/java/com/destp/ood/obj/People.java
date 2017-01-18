package com.destp.ood.obj;

/**
 * Created by liuli10 on 2017/1/12.
 */
public class People {

    private String name;
    private int age;

    public People() {
    }

    public void say(String msg){
        System.out.println("People say : "+msg);
    }

    public void getMyTool(){
        //useTool();
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

    private class Tool{
        String name;

        Tool(String name) {
            this.name = name;
        }

        private void useTool(){
            System.out.println("use tool : "+name);
        }

        private String getTool(){
            return People.this.getName();
        }
    }

    public static void main(String[] args) {
        int x = 10;
        for(int i=0; i<0;i++){
            x++;
            System.out.println(x);
        }
        System.out.println("end - x = "+x);
        System.out.println("7>>>1 = "+(7>>>1));
    }

}
