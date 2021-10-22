package com.ss.basics2.three;

public class Rectangle implements Shape{

    private int length, width;

    public Rectangle(){
        length = 0;
        width = 0;
    }

    public Rectangle(int len, int wid){
        length = len;
        width = wid;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public void display() {
        System.out.println("The area of your rectangle is : " + calculateArea());
    }

    public void setLength(int len){
        length = len;
    }

    public int getLength(){
        return length;
    }

    public void setWidth(int wid){
        width = wid;
    }

    public int getWidth(){
        return width;
    }
    
}
