/**
 * 
 */
package com.zachary.responsive;

import java.io.IOException;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午9:13:36
 */
public class ResponsiveUI extends Thread {
	private static volatile double d = 1;

	@Override
	public void run() {
		while (true) {
			d = (Math.PI * Math.E) / d;
		}
	}

	public ResponsiveUI() {
		super();
		this.setDaemon(true);
		start();
	}

	public static void main(String[] args) throws IOException {
		new ResponsiveUI();
		d = System.in.read();
		Thread.yield();
		System.out.println(d);
	}
}

class UnresponsiveUI {
	private volatile double d = 1;

	public UnresponsiveUI() {
		while (true) {
			d = (Math.PI * Math.E) / d;
		}
//		System.in.read(); // Never gets here
	}

}