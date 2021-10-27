package com.ss.basics4.one;

public class singleton {
    private static singleton instance;

    //because the constructor is private noone can ever make an instance of the class
    private singleton(){}

    public static singleton getInstance(){
        //Check if instance needs to be created
        if(instance == null){
            //If it does, get lock
            synchronized (singleton.class){
                //Then check again to make sure it wasn't created while acquiring lock, and create it if it has not
                if(instance == null){
                    instance = new singleton();
                }
            }
        }
        return instance;
    }
    public void print(){
        System.out.println("Singleton acquired");
    }
}
