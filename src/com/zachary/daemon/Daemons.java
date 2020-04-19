/**
 * 
 */
package com.zachary.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午1:02:43
 */
public class Daemons {
	public static void main(String[] args) {
		Thread thread = new Thread(new ParentDaemon());
		thread.setDaemon(true);
		thread.start();
		System.out.println("thread parent " + thread.isDaemon());
		try {
			TimeUnit.MICROSECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ParentDaemon implements Runnable {
	Thread[] threads = new Thread[5];

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new DaemonSpawn());
			t.start();
			threads[i] = t;
		}
		while (true) {
			for (int i = 0; i < threads.length; i++) {
				Thread thread = threads[i];
				System.out.println("thread[" + i + "] isDaemon: " + thread.isDaemon());
			}
			Thread.yield();
		}
	}
}

class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}