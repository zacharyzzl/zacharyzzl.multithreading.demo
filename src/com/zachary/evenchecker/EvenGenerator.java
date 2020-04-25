package com.zachary.evenchecker;

import com.zachary.generator.IntGenerator;

/**
 * @author Zachary.Zheng
 * @version 1.0
 * @date 2020��4��25�� ����5:31:35
 */
public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	public int next() {
		++currentEvenValue;
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenCaecker.test(new EvenGenerator());
	}
}
