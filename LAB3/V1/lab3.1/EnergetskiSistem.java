package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {
	
	private int column;
	private int row;
	
	private Plac plac;
	private Baterija baterija;
	private Panel buttonPanel = new Panel();
	
	
	private void populateWindow() {
		Button button = new Button("Dodaj");
		
		buttonPanel.add(button);
		plac.setPreferredSize(new Dimension(500,500));
		
		button.addActionListener((ae)->{
			Hidroelektrana h = new Hidroelektrana(baterija);
			plac.dodaj(h);
			h.pokreni();
		});
		
		add(buttonPanel, BorderLayout.NORTH);
		add(plac, BorderLayout.CENTER);
		
		
	}
	
	public EnergetskiSistem(int row, int column, int kap){
		this.row = row;
		this.column = column;
		plac = new Plac(row,column);
		baterija = new Baterija(kap);
		populateWindow();
		setBounds(500, 200, 500, 500);
		setResizable(false);
		setTitle("Energetski sistem");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(plac != null) {
					plac.zaustavi();
				}
				dispose();
			}	
		});
		
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		
		new EnergetskiSistem(5, 5, 2);
	}

}
