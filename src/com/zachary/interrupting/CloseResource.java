package com.zachary.interrupting;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年5月3日 下午2:54:00
 */
public class CloseResource {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InputStream socketInput = new Socket("localhost",8080).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Shutting down all threads");
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + socketInput.getClass().getName());
		socketInput.close();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + System.in.getClass().getName());
		System.in.close(); // 正在读数据，关闭操作会等待读取完成
	}
}

//class IOBlocked implements Runnable {
//	private InputStream in;
//	public IOBlocked(InputStream is) {
//		in = is;
//	}
//	@Override
//	public void run() {
//		try {
//			System.out.println("waiting for read():");
//			in.read();
//		}catch(IOException e) {
//			if(Thread.currentThread().isInterrupted()) {
//				System.out.println("Interrupted for blocked I/O");
//			}else {
//				throw new RuntimeException();
//			}
//		}
//		System.out.println("Exiting IOBlocked.run()");
//	}
//}