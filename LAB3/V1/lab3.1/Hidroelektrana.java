package gui;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {
	
	private int broj;
	
	private class GProizvodnja extends Exception {}

	public Hidroelektrana(Baterija baterija) {
		super('H', Color.BLUE, 1500, baterija);
	}
	
	public void setBroj(int broj) {
		if(broj > 0) this.broj = broj;
		if(this.broj > 0 && broj == -1) this.broj--;
	}

	@Override
	public void proizvediEnerg() throws Exception{
		if(broj == 0) throw new GProizvodnja();
		baterija.dodajEnerg(broj);
	}

}
