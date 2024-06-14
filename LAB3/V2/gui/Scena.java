package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Scena extends Canvas implements Runnable{
	
	private ArrayList<Figura> figure;
	private boolean radi;
	// scena dok radi treba da pomera figure,
	// a dok ne radi treba da ubacuje figure
	private Thread nit = new Thread(this);
	
	public Scena() {
		figure = new ArrayList<>();
		radi = false;
		setBackground(Color.GRAY);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!radi)
					return;
				int x = e.getX();
				int y = e.getY();
				Vektor vektorPolozaja = new Vektor(x,y);
				Vektor vektorPomeraja = Vektor.generisiVektor();
				
				Figura f;
				// verovatno ce modifikacija biti da se ovde bira figura
				f = new Disk(vektorPolozaja, vektorPomeraja);
				dodaj(f);
				repaint();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		for(Figura f: figure) {
			f.iscrtati(this.getGraphics());
		}
		if(!radi) {
			g.setFont(new Font("SansSerif", Font.BOLD,50));
			g.drawString("PAUZA", this.getWidth()/2, this.getHeight()/2);
		}
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(100);
				//while(!radi) wait();
				
				// promeniti polozaje figura
				// proveriti da li su se neke odbile o zid
				// proveriti da li su se neke odbile jedna o drugu
				
				//repaint();
			}
		} catch (InterruptedException e) {}
		
	}
	
	public void pokreni() {
		nit.start();
		radi = true;
	}
	
	public void pauziraj() {
		radi = false;
		//notify();
	}
	
	public void nastavi() {
		radi = true;
		//notify();
	}
	
	public void zaustavi() {
		nit.interrupt();
	}
	
	public boolean radi() {
		return radi;
	}
	
	public void dodaj(Figura f) {
		// prvo proveravamo da li se figura f preklapa sa nekom vec postojecom
		boolean preklapa = false;
		for(Figura fig: figure) {
			if(fig.preklapa(f)) {
				preklapa =true;
				break;
			}
		}
		
		if(preklapa)
			return;
		
		// proveravamo da li figura staje na scenu
		double poluprecnik = f.getPoluprecnik();
		double x = f.getVektorPolozaja().getX();
		double y = f.getVektorPolozaja().getY();
		if(x<poluprecnik || x>this.getWidth()-poluprecnik)
			return;
		
		if(y<poluprecnik || y>this.getHeight()-poluprecnik)
			return;
		
		figure.add(f);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
