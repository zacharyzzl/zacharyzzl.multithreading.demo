/**
 * 
 */
package com.zachary.join;

import java.time.LocalDateTime;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午8:41:44
 */
public class Joining {
	public static void main(String[] args) {
		System.out.println("begin time: " + LocalDateTime.now());
		Sleeper sleeper1 = new Sleeper("sleeper1", 1500), sleeper2 = new Sleeper("sleeper2", 1500);
		new Joiner(sleeper1, "joiner1");
		new Joiner(sleeper2, "joiner2");
		sleeper1.interrupt();
	}
}

class Sleeper extends Thread {
	private int duration;

	@Override
	public void run() {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(this.getName() + " sleep InterruptedException" + LocalDateTime.now());
			return;
		}
		System.out.println(this.getName() + " sleep completed" + LocalDateTime.now());
	}

	public Sleeper(String name, int sleepTime) {
		super(name);
		this.duration = sleepTime;
		start();
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(Sleeper sleeper, String name) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println(this.getName() + " join interruptedException" + LocalDateTime.now());
			return;
		}
		System.out.println(this.getName() + " join completed" + LocalDateTime.now());
	}
}
