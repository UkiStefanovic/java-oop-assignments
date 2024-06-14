package gui;

import java.awt.Color;

public abstract class Proizvodjac extends Parcela implements Runnable {
	

	private int osnovnoVreme;
	protected Baterija baterija;
	private int ukupnoVreme;
	protected Thread nit = new Thread(this);

	public Proizvodjac(char oznaka, Color color,int vreme,Baterija baterija) {
		super(oznaka, color);
		this.osnovnoVreme = vreme;
		this.baterija = baterija;
		ukupnoVreme = osnovnoVreme + (int)Math.floor(Math.random() * 301); 
	}

	@Override
	public void run() {
		try {
			while(!nit.isInterrupted()) {
				nit.sleep(ukupnoVreme);
				synchronized(this) {
					proizvediEnerg();
					setForeground(Color.RED);
				}
				nit.sleep(300);
				setForeground(Color.WHITE);
			}
		} catch (InterruptedException e) {
		} catch (Exception e) {}
	}

	public abstract void proizvediEnerg() throws Exception;
	public abstract void setBroj(int broj);
	
	public void zaustavi() {
		nit.interrupt();
	}
	public void pokreni() {
		nit.start();
	}

}
