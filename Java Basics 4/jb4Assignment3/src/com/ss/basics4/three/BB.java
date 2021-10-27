package com.ss.basics4.three;

import java.util.LinkedList;

public class BB {
	Integer size, runs;
	LinkedList<Integer> data = new LinkedList<>();
	
	public BB(int size, int runs) {
		this.size = size;
		this.runs = runs;
	}
	
	public synchronized void make() throws InterruptedException {
		for(int i = 0; i < runs; i++) {
			//when data is full, wait
			if(data.size() == size) wait();
			//generate a random value to put in data
			int value = (int)((Math.random()*100)+1);
			System.out.println("Produced : " + value);
			data.add(value);
			notify();
			//sleep line to more cleanly watch output
			//Thread.sleep(1000);
		}
	}
	
	public synchronized void take() throws InterruptedException {
		for(int i = 0; i < runs; i++) {
			//when data is empty, wait
			if(data.size() == 0) wait();
			//consume values from oldest to newest
			int value = data.removeFirst();
			System.out.println("Consumed : " + value);
			notify();
			//sleep line to more cleanly watch output
			//Thread.sleep(1000);
		}
	}
}
