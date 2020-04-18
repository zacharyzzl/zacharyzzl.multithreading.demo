/**
 * 
 */
package com.zachary.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 上午12:20:43
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}

}
