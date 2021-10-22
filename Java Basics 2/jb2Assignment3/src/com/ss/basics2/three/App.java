package com.ss.basics2.three;
public class App {
    public static void main(String[] args) throws Exception {
        Circle cir = new Circle(5);
        cir.calculateArea();
        cir.display();

        Rectangle rect = new Rectangle(5,5);
        rect.calculateArea();
        rect.display();

        Triangle tri = new Triangle(5,5);
        tri.calculateArea();
        tri.display();
    }
}
