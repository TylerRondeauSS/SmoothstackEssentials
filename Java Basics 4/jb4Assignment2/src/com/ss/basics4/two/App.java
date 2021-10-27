package com.ss.basics4.two;

public class App {
    public static void main(String[] args) throws Exception {
        Integer a = 1, b = 10;
        Runnable t1 = new Runnable(){
            public void run(){
                try{
                    synchronized(a){
                        Thread.sleep(1000);
                        synchronized(b){
                            System.out.println("a + b = " + (a+b));
                        }
                    }
                }
                catch(Exception e){System.err.println("Thread interrupted");}
            }
        };
        Runnable t2 = new Runnable(){
            public void run(){
                try{
                    synchronized(b){
                        Thread.sleep(100);
                        synchronized(a){
                            System.out.println("a * b = " + (a*b));
                        }
                    }
                }
                catch(Exception e){System.err.println("Thread interrupted");}
            }
        };
        System.out.println("Starting Deadlock");
        new Thread(t1).start();
        new Thread(t2).start();
        System.out.println("Ending Deadlock");
    }
}
