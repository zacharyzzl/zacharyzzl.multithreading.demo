/**
 * 
 */
package com.zachary.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月18日 下午11:09:50
 */
public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d;
	private int priopty;

	public SimplePriorities(int priopty) {
		super();
		this.priopty = priopty;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priopty);
		while (true) {
			for (int i = 0; i < 10000; i++) {
				d += (Math.PI * Math.E) / d;
				if (i == 1000) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if(--countDown <= 0) {
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "SimplePriorities [Thread.currentThread=" + Thread.currentThread() + ",countDown=" + countDown
				+ ",  priopty=" + priopty + "]";
	}
	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			cachedThreadPool.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		cachedThreadPool.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		cachedThreadPool.execute(new SimplePriorities(Thread.NORM_PRIORITY));
		cachedThreadPool.shutdown();
	}
}
