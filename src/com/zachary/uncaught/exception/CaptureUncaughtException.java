package com.zachary.uncaught.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月25日 下午3:12:11
 */
public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool(new HandlerThreadFactory());
		executor.execute(new ExceptionThread2());
		executor.shutdown();
	}
}

class ExceptionThread2 implements Runnable {
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run by " + t);
		System.out.println("eh " + t.getUncaughtExceptionHandler());
		System.out.println("default eh " + t.getDefaultUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}

class HandlerThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + "create new Thread");
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh " + t.getUncaughtExceptionHandler());
		return t;
	}
}