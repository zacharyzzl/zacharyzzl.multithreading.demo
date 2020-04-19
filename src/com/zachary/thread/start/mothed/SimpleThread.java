/**
 * 
 */
package com.zachary.thread.start.mothed;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午5:38:22
 */
public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	
	@Override
	public void run() {
		while(true) {
			System.out.println(this);
			if(--countDown == 0) {
				return;
			}
		}
	}
	
	@Override
	public String toString() {
		return "SimpleThread [countDown=" + countDown + "name: "+this.getName()+"]";
	}

	public SimpleThread() {
		super(Integer.toString(++threadCount));
		this.start();
	}
	public static void main(String [] args) {
		for(int i=0; i<5; i++) {
			new SimpleThread();
		}
	}
}
