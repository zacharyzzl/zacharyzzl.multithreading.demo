/**
 * 
 */
package com.zachary.thread.start.mothed;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午5:50:41
 */
public class SelfManager implements Runnable {
	private int countDown = 5;
	private Thread t = new Thread(this);

	public SelfManager() {
		t.start();
	}

	@Override
	public String toString() {
		return "name: " + t.getName() + ";countDown: " + countDown;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SelfManager();
		}
	}
}
