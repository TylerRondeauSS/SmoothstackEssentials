package com.ss.basics2.three;

public class Rectangle implements Shape{

    private Integer length, width;

    public Rectangle(){
        length = 0;
        width = 0;
    }

    public Rectangle(Integer len, Integer wid){
        length = len;
        width = wid;
    }

    @Override
    public Double calculateArea() {
        return (double) (length * width);
    }

    @Override
    public void display() {
        System.out.println("The area of your rectangle is : " + calculateArea());
    }

    public void setLength(Integer len){
        length = len;
    }

    public Integer getLength(){
        return length;
    }

    public void setWidth(Integer wid){
        width = wid;
    }

    public Integer getWidth(){
        return width;
    }
    
}
