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
 * @date 2020年4月12日 下午11:26:33
 */
public class MyFixedThreadPool {
	public static void main(String[] args) throws Exception {
		runnableTest();
		callableTest();
	}

	public static void runnableTest() {
		ExecutorService es = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			es.execute(new MyRunnable());
		}
		es.shutdown();
		System.out.println("shutdown after");
	}

	public static void callableTest() throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(5);
		List<Future<Object>> reList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			reList.add(es.submit(new MyCallable(i + "")));
		}
		es.shutdown();
		for (Future<Object> future : reList) {
			System.out.println(future.get());
		}
		System.out.println("shutdown after");
	}
}
