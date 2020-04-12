/**
 * 
 */
package com.zachary.test;

import java.time.LocalDateTime;

import com.zachary.task.MyRunnable;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月12日 下午10:37:49
 */
public class ThreadTest {
	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
		for (int i = 0; i < 5; i++) {
			new Thread(new MyRunnable()).start();
		}
		System.out.println("ThreadTest main over!");
		System.out.println(LocalDateTime.now());
	}
}
