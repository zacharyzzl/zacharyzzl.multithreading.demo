package com.zachary.generator;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020年4月25日 下午5:12:24
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;

	public abstract int next();

	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}
