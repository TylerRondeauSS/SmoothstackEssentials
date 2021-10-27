package com.ss.basics4.three;

public class App {
    public static void main(String[] args) throws Exception {
    	
    	BB tester = new BB(3,10);
    	//Producer thread creation
        Thread prod = new Thread(new Runnable() {
        	@Override
        	public void run() {
        		try {
        			tester.make();
        		}
        		catch(InterruptedException e ){System.err.println("Thread Interrupted");}
        	}
        	//Consumer thread creation
        }), con = new Thread(new Runnable() {
        	@Override
        	public void run() {
        		try {
        			tester.take();
        		}
        		catch(InterruptedException e ){System.err.println("Thread Interrupted");}
        	}
        });
        prod.start();
        con.start();
    }
}
