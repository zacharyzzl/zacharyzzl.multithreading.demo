/**
 * 
 */
package com.zachary.task;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月12日 下午10:33:22
 */
public class MyRunnable implements Runnable {
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;
	
	public MyRunnable() {
		super();
	}

	public MyRunnable(int countDown) {
		this.countDown = countDown;
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this);
		}
	}

	@Override
	public String toString() {
		return "MyRunnable [id=" + id + ", countDown=" + countDown + ", taskCount=" + taskCount + "]";
	}
	
}
