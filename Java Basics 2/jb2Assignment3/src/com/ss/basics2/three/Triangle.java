package com.ss.basics2.three;

public class Triangle implements Shape{
    
    private int base, height;

    public Triangle(){
        height = 0;
        base = 0;
    }

    public Triangle(int hi, int bse){
        height = hi;
        base = bse;
    }

    @Override
    public double calculateArea() {
        return (height * base) / 2;
    }

    @Override
    public void display() {
        System.out.println("The area of your Trinagle is : " + calculateArea());
    }

    public void setHeight(int hi){
        height = hi;
    }

    public int getHeight(){
        return height;
    }

    public void setBase(int bse){
        base = bse;
    }

    public int getBase(){
        return base;
    }
    
}
