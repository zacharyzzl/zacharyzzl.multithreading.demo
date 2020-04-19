/**
 * 
 */
package com.zachary.daemon;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午1:29:03
 */
public class ADaemon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("carry out start");
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("carry out error");
			e.printStackTrace();
		} finally {
			System.out.println("carry out finally");
		}
	}

	public static void main(String [] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}
}
