package com.java.osttra;

class SharedResource {
	public int num;
	public boolean flag = false;

	public synchronized void put(int num) {
		if (flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Producer - " + num);
		this.num = num;
		flag = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		flag = true;
		notifyAll();
	}

	public synchronized int get() {
		if (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Consumer - " + num);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		flag = false;
		notifyAll();
		return num;
	}
}

class Producer implements Runnable {

	SharedResource s;

	public Producer(SharedResource s) {
		this.s = s;
		new Thread(this).start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			s.put(i++);
		}
	}
}

class Consumer implements Runnable {

	SharedResource s;

	public Consumer(SharedResource s) {
		this.s = s;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			s.get();
		}
	}

}

public class ProducerConsumerProblem {

	public static void main(String[] args) {
		SharedResource s = new SharedResource();
		new Producer(s);
		new Consumer(s);

	}

}
