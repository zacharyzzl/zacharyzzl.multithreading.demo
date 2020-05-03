package com.zachary.interrupting;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年5月3日 下午2:22:30
 */
public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrup send to " + r.getClass().getName());
	}
	public static void main(String[] args) throws InterruptedException {
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		test(new SleepBlocked());
		TimeUnit.MILLISECONDS.sleep(1000);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}
class SleepBlocked implements Runnable{

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptingException");
		}
		System.out.println("Exting SleepBlocked.run()");
	}
	
}

class IOBlocked implements Runnable {
	private InputStream in;

	public IOBlocked(InputStream is) {
		in = is;
	}

	@Override
	public void run() {
		try {
			System.out.println("waiting for read():");
			in.read();
		}catch(IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted for blocked I/O");
			}else {
				throw new RuntimeException();
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}
}

class SynchronizedBlocked implements Runnable{
	
	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f();
			}
		}.start();
	}
	@Override
	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}
	public synchronized void f() {
		while(true) {
			Thread.yield();
		}
	}
}