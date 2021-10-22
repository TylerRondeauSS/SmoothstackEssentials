package com.ss.basics2.three;

public class Triangle implements Shape{
    
    private Integer base, height;

    public Triangle(){
        height = 0;
        base = 0;
    }

    public Triangle(Integer hi, Integer bse){
        height = hi;
        base = bse;
    }

    @Override
    public Double calculateArea() {
        return (double) ((height * base) / 2);
    }

    @Override
    public void display() {
        System.out.println("The area of your Trinagle is : " + calculateArea());
    }

    public void setHeight(Integer hi){
        height = hi;
    }

    public Integer getHeight(){
        return height;
    }

    public void setBase(Integer bse){
        base = bse;
    }

    public Integer getBase(){
        return base;
    }
    
}
