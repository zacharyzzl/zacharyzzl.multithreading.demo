package com.zachary.evenchecker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zachary.generator.IntGenerator;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月25日 下午5:17:49
 */
public class EvenCaecker implements Runnable {
	private IntGenerator generator;
	private final int id;
	public EvenCaecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}
	@Override
	public void run() {
		while(!generator.isCanceled()) {
			int val = generator.next();
			if( val % 2 != 0) {
				System.out.println(val + "not even!");
				generator.cancel();
			}
		}
	}
	
	public static void test(IntGenerator g, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			executor.execute(new EvenCaecker(g, i));
		}
		executor.shutdown();
	}
	public static void test(IntGenerator g) {
		test(g, 10);
	}
}
