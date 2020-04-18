/**
 * 
 */
package com.zachary.daemon;

import java.util.concurrent.ExecutorService;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 上午12:44:40
 */
public class DaemonFromTreadPoolExecutor implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread() + " " + this);
		}
	}
	public static void main(String [] args) {
		ExecutorService executor = new DaemonThreadPoolExecutor();
		for (int i = 0; i < 5; i++) {
			executor.execute(new DaemonFormFactory());
		}
		executor.shutdown();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
