package com.ss.basics2.three;

public class Circle implements Shape{
    private Integer radius;

    public Circle(){
        radius = 0;
    }

    public Circle(Integer rad){
        radius = rad;
    }

    @Override
    public Double calculateArea() {
        return (this.radius*this.radius)*Math.PI;
    }

    @Override
    public void display() {
        System.out.println("The area of your circle is : " + calculateArea());
    }

    public void setRadius(Integer rad){
        radius = rad;
    }

    public Integer getRadius(){
        return radius;
    }
    
}
