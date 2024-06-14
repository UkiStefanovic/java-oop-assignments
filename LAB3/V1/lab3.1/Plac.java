package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Plac extends Panel{
	
	private int row;
	private int column;
	
	private ArrayList<Parcela> resetka;

	public Plac(int row, int column) {
		this.row = row;
		this.column = column;
		resetka = new ArrayList<Parcela>(row*column);
		setLayout(new GridLayout(row,column, 4 , 4));
		
		for(int i = 0; i < row*column; i++) {
			int x = (int)Math.floor(Math.random() * 10);
			if(x < 7) resetka.add(new TravnataPovrs());
			else resetka.add(new VodenaPovrs());
			resetka.get(i).setSize(500/column, 500/row);
			add(resetka.get(i));
		
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Parcela parcela=(Parcela)e.getSource();
				for(int i = 0; i < resetka.size(); i++) {
					if(parcela == resetka.get(i)) {
						resetka.get(i).setFont(new Font(Font.SERIF,Font.BOLD, 20));
					}
					else resetka.get(i).setFont(new Font(Font.SERIF,Font.BOLD, 14));
				}
			}
		});	
	}
	
	public void zaustavi() {
		for(int i = 0; i < resetka.size(); i++) {
			if(resetka.get(i) instanceof Proizvodjac) {
				Proizvodjac p = (Proizvodjac)resetka.get(i);
				p.zaustavi();
			}
		}
	}
	
	private int brojac(int i, boolean flag) {
		int brojac = 0;
		if(flag) {
			if(i + column < row*column)
				if((resetka.get(i+column) instanceof VodenaPovrs)) brojac++;
			if(i - column >= 0) 
				if((resetka.get(i-column) instanceof VodenaPovrs)) brojac++;
			if( i % column != 0 ){
				if((i + column - 1 < row*column ) && (resetka.get(i + column - 1) instanceof VodenaPovrs)) brojac++;
				if((i - column - 1 >= 0 ) && (resetka.get(i - column - 1) instanceof VodenaPovrs)) brojac++;
				if((resetka.get(i-1) instanceof VodenaPovrs) && (i % column != 0)) brojac++;
			}
			if( i % column != column - 1 ){
				if((i + column + 1 < row*column ) && (resetka.get(i + column + 1) instanceof VodenaPovrs)) brojac++;
				if((i - column + 1 >= 0 ) && (resetka.get(i - column + 1) instanceof VodenaPovrs)) brojac++;
				if((resetka.get(i+1) instanceof VodenaPovrs) && (i % column != column - 1)) brojac++;
			}
		}else {
			if(i + column < row*column)
				if((resetka.get(i+column) instanceof TravnataPovrs)) brojac++;
			if(i - column >= 0) 
				if((resetka.get(i-column) instanceof TravnataPovrs)) brojac++;
			if( i % column != 0 ){
				if((i + column - 1 < row*column ) && (resetka.get(i + column - 1) instanceof TravnataPovrs)) brojac++;
				if((i - column - 1 >= 0 ) && (resetka.get(i - column - 1) instanceof TravnataPovrs)) brojac++;
				if((resetka.get(i-1) instanceof TravnataPovrs) && (i % column != 0)) brojac++;
			}
			if( i % column != column - 1 ){
				if((i + column + 1 < row*column ) && (resetka.get(i + column + 1) instanceof TravnataPovrs)) brojac++;
				if((i - column + 1 >= 0 ) && (resetka.get(i - column + 1) instanceof TravnataPovrs)) brojac++;
				if((resetka.get(i+1) instanceof TravnataPovrs) && (i % column != column - 1)) brojac++;
			}
		}
		return brojac;
	}
	
	private void susedi(int i){
		if(i + column < row*column)
			if((resetka.get(i + column) instanceof Proizvodjac)) ((Proizvodjac)resetka.get(i + column)).setBroj(-1);
		if(i - column >= 0) 
			if((resetka.get(i - column) instanceof Proizvodjac)) ((Proizvodjac)resetka.get(i - column)).setBroj(-1);
		if( i % column != 0 ){
			if((i + column - 1 < row*column ) && (resetka.get(i + column - 1) instanceof Proizvodjac)) ((Proizvodjac)resetka.get(i + column-1)).setBroj(-1);
			if((i - column - 1 >= 0 ) && (resetka.get(i - column - 1) instanceof Proizvodjac)) ((Proizvodjac)resetka.get(i - column-1)).setBroj(-1);
			if((resetka.get(i-1) instanceof Proizvodjac) && (i % column != 0)) ((Proizvodjac)resetka.get(i - 1)).setBroj(-1);
		}
		if( i % column != column - 1 ){
			if((i + column + 1 < row*column ) && (resetka.get(i + column + 1) instanceof Proizvodjac)) ((Proizvodjac)resetka.get(i + column + 1)).setBroj(-1);
			if((i - column + 1 >= 0 ) && (resetka.get(i - column + 1) instanceof Proizvodjac)) ((Proizvodjac)resetka.get(i - column + 1)).setBroj(-1);
			if((resetka.get(i+1) instanceof Proizvodjac) && (i % column != column -1)) ((Proizvodjac)resetka.get(i + 1)).setBroj(-1);
		}
	}

	public boolean dodaj(Proizvodjac p) {
		for(int i = 0; i < row*column; i++) {
			if(resetka.get(i).getFont().getSize() == 20) {
				boolean flagVoda = false;
				if(resetka.get(i) instanceof VodenaPovrs) flagVoda = true;
				p.setBroj(brojac(i,p instanceof Hidroelektrana));
				p.setFont((new Font(Font.SERIF,Font.BOLD, 14)));
				this.remove(i);
				this.add(p, i);
				resetka.set(i, p);
				revalidate();
				if(flagVoda || (!flagVoda && !(p instanceof Hidroelektrana))) susedi(i);
				if (brojac(i,p instanceof Hidroelektrana) <= 0) return false;
				return true;
			}
		}
		return false;
	}
}
