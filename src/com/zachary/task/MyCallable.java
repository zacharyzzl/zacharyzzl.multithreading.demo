/**
 * 
 */
package com.zachary.task;

import java.util.concurrent.Callable;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月12日 下午10:33:22
 */
public class MyCallable implements Callable<Object> {
	private String param;
	
	public MyCallable() {
		super();
	}

	public MyCallable(String param) {
		super();
		this.param = param;
	}

	@Override
	public Object call() throws Exception {
		Thread.sleep(1000);
		return "handled : " + param;
	}

}