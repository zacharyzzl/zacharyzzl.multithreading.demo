package com.zachary.uncaught.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020��4��25�� ����4:13:08
 */
public class SettingDefaultHandler {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new ExceptionThread2());
	}
}
