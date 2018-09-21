package Controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadSala extends Thread {

	private int num_pessoa;
	Semaphore semaforo;
	private int num_corredor;
	private int distanciaPerc;
	private int distanciaMax;
	private static int ordemChegada, ordemSaida;

	public ThreadSala(int num_pessoa, int num_corredor, Semaphore semaforo) {

		this.num_pessoa = num_pessoa;
		this.num_corredor = num_corredor;
		this.semaforo = semaforo;

	}

	public void run() {

		Corredor();
		Cruzamento();

		try {

			semaforo.acquire();
			//Esperando();
			Saindo();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			semaforo.release();
		} 

	}

	public void Corredor() {

		System.out.println("A Pessoa " + num_pessoa + "º Está no " + num_corredor + " Corredor");
	}

	public void Cruzamento() {

		Random r = new Random();
		int tempo_percurso = r.nextInt(1001) + 1000;
		int passagem_pessoa;
		distanciaMax = 200;

		while (distanciaPerc < distanciaMax) {

			passagem_pessoa = r.nextInt(3) + 4;
			distanciaPerc = distanciaPerc + passagem_pessoa;
			if (distanciaPerc > distanciaMax) {
				
				distanciaPerc = distanciaMax;
			}
			
			System.out.println("A Pessoa: " + num_pessoa + " andou: " + passagem_pessoa + "m/s" + " Percorrendo: "
					+ distanciaPerc + "m");
		}


		try {
			Thread.sleep(tempo_percurso);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		ordemChegada++;
		System.out.println("A " + num_pessoa + "º Pessoa foi a " + ordemChegada + "º a Chegar");
	}

	public void Esperando() {

		Random r = new Random();
		int intervalo = r.nextInt(1001) + 1000;
		System.out.println("A Pessoa " + num_pessoa + "º Está Aguardando...");
		try {
			Thread.sleep(intervalo);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public void Saindo() {

		Random r = new Random();
		int intervalo = r.nextInt(1001) + 1000;

		ordemSaida++;
		System.out.println("A " + num_pessoa + "º Pessoa " + num_pessoa + " foi a " + ordemSaida + " a sair");

		try {
			Thread.sleep(intervalo);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
}
