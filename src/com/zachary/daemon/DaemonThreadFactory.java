/**
 * 
 */
package com.zachary.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020��4��19�� ����12:20:43
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}

}
