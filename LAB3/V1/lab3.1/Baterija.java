package gui;

public class Baterija {
	
	private int kapacitet;
	private int energija;
	
	public Baterija(int maks) {
		this.kapacitet = maks;
		this.energija = maks;
	}

	public void dodajEnerg(int e) {
		energija += e;
		if(energija > kapacitet) energija = kapacitet;	
    }
	
	public void isprazniBat() { energija = 0;}
	public boolean baterijaPuna() { return energija==kapacitet; }

}
