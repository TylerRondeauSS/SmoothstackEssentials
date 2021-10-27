package com.ss.basics4.one;

public class App {
    public static void main(String[] args) throws Exception {
        singleton ex = singleton.getInstance();
        ex.print();
    }
}
