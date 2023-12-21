package com.java.osttra;

public class TicketBooking implements Runnable {

	private int ticket = 5;

	public static void main(String[] args) {

		TicketBooking booking = new TicketBooking();

		new Thread(booking, "Customer 1").start();
		new Thread(booking, "Customer 2").start();
		new Thread(booking, "Customer 3").start();
		new Thread(booking, "Customer 4").start();
		new Thread(booking, "Customer 5").start();
		new Thread(booking, "Customer 6").start();
		new Thread(booking, "Customer 7").start();
		new Thread(booking, "Customer 8").start();
		new Thread(booking, "Customer 9").start();

	}

	@Override
	public synchronized void run() {

		//System.out.println("Waiting for ticket to book " + Thread.currentThread().getName());

		if (ticket > 0) {

			//System.out.println("Booking ticket for " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ticket--;
			System.out.println("Ticket booked for " + Thread.currentThread().getName());
			//System.out.println("Current ticket available " + ticket);
		} else {
			System.out.println("Ticket not booked for " + Thread.currentThread().getName());
		}

	}

}
