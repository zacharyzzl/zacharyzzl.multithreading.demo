/**
 * 
 */
package com.zachary.thread.start.mothed;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月19日 下午6:37:16
 */
public class ThreadVariation {
	public static void main(String[] args) {
		new InnerThread1("innerThread1");
		new InnerThread2("innerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
		try {
			System.out.println(new InnerCallable1("InnerCallable1").getInner().getFuture().get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(new InnerCallable2("InnerCallable2").getFuture().get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class InnerThread1 {
	private int countDown = 5;

	class Inner extends Thread {
		public Inner(String name) {
			super(name);
			start();
		}

		@Override
		public void run() {
			while (true) {
				System.out.println(this);
				if (--countDown == 0) {
					return;
				}
			}
		}

		@Override
		public String toString() {
			return "name=" + this.getName() + " : " + countDown;
		}
	}

	public InnerThread1(String name) {
		new Inner(name);
	}
}

class InnerThread2 {
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
				}
			}

			@Override
			public String toString() {
				return "name=" + t.getName() + " : " + countDown;
			}
		};
		t.start();
	}
}

class InnerRunnable1 {
	private int countDown = 5;

	class Inner implements Runnable {
		private Thread t;

		@Override
		public void run() {
			while (true) {
				System.out.println(this);
				if (--countDown == 0) {
					return;
				}
			}
		}

		@Override
		public String toString() {
			return "name=" + t.getName() + " : " + countDown;
		}

		public Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}
	}

	public InnerRunnable1(String name) {
		new Inner(name);
	}
}

class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;

	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
				}
			}

			@Override
			public String toString() {
				return "name=" + t.getName() + " : " + countDown;
			}
		}, name);
		t.start();
	}
}

class InnerCallable1 {
	private Inner inner;

	class Inner implements Callable<String> {
		private Future<String> f;
		private String name;

		public Inner(String name) {
			super();
			this.name = name;
			ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
			f = singleThreadExecutor.submit(this);
			singleThreadExecutor.shutdown();
		}

		@Override
		public String call() throws Exception {
			return "name=" + name;
		}

		public Future<String> getFuture() {
			return f;
		}
	}

	public Inner getInner() {
		return inner;
	}

	public InnerCallable1(String name) {
		inner = new Inner(name);
	}
}

class InnerCallable2 {
	private Future<String> f;
	ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	public InnerCallable2(String name) {
		f = singleThreadExecutor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "name=" + name;
			}

		});
		singleThreadExecutor.shutdown();
	}
	public Future<String> getFuture() {
		return f;
	}
}

class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		super();
		this.name = name;
	}

	public void runTask() {
		t = new Thread(this.name) {
			@Override
			public void run() {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
				}
			}

			@Override
			public String toString() {
				return "name=" + t.getName() + " : " + countDown;
			}
		};
		t.start();
	}
}
