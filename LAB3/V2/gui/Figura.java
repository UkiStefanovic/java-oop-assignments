package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	protected Vektor vektorPolozaja;
	protected Vektor vektorPomeraja;
	protected double poluprecnik = 20;
	
	public Figura(Vektor vektorPolozaja, Vektor vektorPomeraja) {
		this.vektorPolozaja = vektorPolozaja;
		this.vektorPomeraja = vektorPomeraja;
	}
	
	public Figura(Vektor vektorPolozaja, Vektor vektorPomeraja, double poluprecnikOpisaneKruznice) {
		this(vektorPolozaja, vektorPomeraja);
		this.poluprecnik = poluprecnikOpisaneKruznice;
	}
	
	public Vektor getVektorPolozaja() {
		return vektorPolozaja;
	}
	public void setVektorPolozaja(Vektor vektorPolozaja) {
		this.vektorPolozaja = vektorPolozaja;
	}

	public Vektor getVektorPomeraja() {
		return vektorPomeraja;
	}
	public void setVektorPomeraja(Vektor vektorPomeraja) {
		this.vektorPomeraja = vektorPomeraja;
	}

	public double getPoluprecnik() {
		return poluprecnik;
	}
	public void setPoluprecnik(double poluprecnik) {
		this.poluprecnik = poluprecnik;
	}
	
	public boolean unutarFigure(Vektor vektorPolozaja) {
		// ako je rastojanje izmedju vektoraPolozaja tekuce figure i zadatog vektora polozaja
		// manje od poluprecnika tekuce figure, vraca true
		double rastojanjeX = vektorPolozaja.getX()-this.vektorPomeraja.getX();
		double rastojanjeY = vektorPolozaja.getY()-this.vektorPomeraja.getY();
		double rastojanje = Math.sqrt(Math.pow(rastojanjeX, 2)+ Math.pow(rastojanjeY, 2));
		if(rastojanje <= this.poluprecnik)
			return true;
		else 
			return false;
	}
	
	public boolean preklapa(Figura figura) {
		// ako je rastojanje izmedju vektoraPolozaja ove dve figure manje od
		// zbira njihovih poluprecnika, vraca true
		double rastojanjeX = figura.vektorPolozaja.getX()-this.vektorPomeraja.getX();
		double rastojanjeY = figura.vektorPolozaja.getY()-this.vektorPomeraja.getY();
		double rastojanje = Math.sqrt(Math.pow(rastojanjeX, 2)+ Math.pow(rastojanjeY, 2));
		if(rastojanje<=this.poluprecnik+figura.poluprecnik)
			return true;
		else
			return false;
	}
	
	public abstract Color boja();
	public abstract void iscrtati(Graphics g);
	

	public static void main(String[] args) {

	}

}
