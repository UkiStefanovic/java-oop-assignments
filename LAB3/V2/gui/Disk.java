package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Disk extends Figura {
	
	public Disk(Vektor vektorPolozaja, Vektor vektorPomeraja) {
		super(vektorPolozaja, vektorPomeraja);
	}
	
	public Disk(Vektor vektorPolozaja, Vektor vektorPomeraja, double poluprecnikOpisaneKruznice) {
		super(vektorPolozaja, vektorPomeraja, poluprecnikOpisaneKruznice);
	}

	@Override
	public void iscrtati(Graphics g) {
		
		Polygon p = new Polygon();
		int centarX = (int)this.vektorPolozaja.getX();
		int centarY = (int)this.vektorPolozaja.getY();
		int poluprecnikDiska = (int)this.poluprecnik;
		int vrednost = (int)(Math.sqrt(2)/2*this.poluprecnik);
		
		p.addPoint(centarX, centarY-poluprecnikDiska);
		p.addPoint(centarX+vrednost, centarY-vrednost);
		p.addPoint(centarX+poluprecnikDiska, centarY);
		p.addPoint(centarX+vrednost, centarY+vrednost);
		p.addPoint(centarX, centarY+poluprecnikDiska);
		p.addPoint(centarX-vrednost, centarY+vrednost);
		p.addPoint(centarX-poluprecnikDiska, centarY);
		p.addPoint(centarX-vrednost, centarY-vrednost);
		
		g.setColor(this.boja());
		g.fillPolygon(p);
	}
	
	@Override
	public Color boja() {
		return Color.BLUE;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
