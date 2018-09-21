package view;

import java.util.concurrent.Semaphore;

import Controller.ThreadSala;

public class Principal {

	public static void main(String[] args) {

		int estagios = 1;
		Semaphore sem = new Semaphore(estagios);
		
		for(int i = 1; i <= 4; i++) {
		
			Thread ts = new  ThreadSala(i, i, sem);
			ts.start();
		}
	}
}
