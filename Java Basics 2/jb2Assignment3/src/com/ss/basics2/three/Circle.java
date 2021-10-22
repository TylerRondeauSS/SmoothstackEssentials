package com.ss.basics2.three;

public class Circle implements Shape{
    private int radius;

    public Circle(){
        radius = 0;
    }

    public Circle(int rad){
        radius = rad;
    }

    @Override
    public double calculateArea() {
        return (this.radius*this.radius)*Math.PI;
    }

    @Override
    public void display() {
        System.out.println("The area of your circle is : " + calculateArea());
    }

    public void setRadius(int rad){
        radius = rad;
    }

    public int getRadius(){
        return radius;
    }
    
}
