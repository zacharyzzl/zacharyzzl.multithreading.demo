/**
 * 
 */
package com.zachary.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.zachary.task.MyCallable;
import com.zachary.task.MyRunnable;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月12日 下午10:55:28
 */
public class MyCachedThreadPool {
	public static void main(String[] args) throws Exception {
//		runnableTest();
		callableTest();
	}

	public static void runnableTest() {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			es.execute(new MyRunnable());
		}
		System.out.println("shutdown before");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("sleep 500 end"); // 还没有执行shutdown是线程已经开始跑了
		es.shutdown();
		System.out.println("shutdown after");
	}

	public static void callableTest() throws Exception {
		ExecutorService es = Executors.newCachedThreadPool();
		List<Future<Object>> reList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			reList.add(es.submit(new MyCallable(i + "")));
		}
		for (Future<Object> future : reList) {
			System.out.println(future.get());
		}
		es.shutdown();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}
