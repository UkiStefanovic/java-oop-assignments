package gui;

public class Vektor implements Cloneable{
	private double x;
	private double y;
	
	public Vektor(double x, double y) {
		if(x==0 & y==0) {
			this.x = 1;
			this.y = 0;
			return;
		}
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		if(y==0 & x==0)
			return;
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		if(x==0 & y==0)
			return;
		this.y = y;
	}
	
	public double magnituda() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	
	@Override
	protected Vektor clone() throws CloneNotSupportedException {
		return (Vektor)super.clone();
	}
	
	public Vektor ort() {
		double x = getX();
		double y = getY();
		x /= magnituda();
		y /= magnituda();
		Vektor rez = new Vektor(x,y);
		return rez;
	}
	
	public static Vektor generisiVektor() {
		double x = Math.random()*2-1;
		double y = Math.random()*2-1;
		while(x==0 & y==0) {
			x = Math.random()*2-1;
			y = Math.random()*2-1;
		}
		Vektor rez = new Vektor(x,y);
		return rez;
	}
	
	
	
	public static void main(String[] args) {
		Vektor v1 = new Vektor(1,1);
		System.out.println(v1.magnituda());
		System.out.println(v1.ort().magnituda());
		Vektor v2 = Vektor.generisiVektor();
		System.out.println(v2.x +" "+v2.y);
	}

}
