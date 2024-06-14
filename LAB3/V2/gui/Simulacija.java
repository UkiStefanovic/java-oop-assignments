package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulacija extends Frame {
	
	private Scena scena;
	
	public Simulacija() {
		setBounds(200,200,500,500);
		setResizable(false);
		setTitle("Simulacija");
		
		// populateWindow
		scena = new Scena();
		add(scena, BorderLayout.CENTER);
		scena.pokreni();
		
		
		// treba dodati osluskivace za mis i tastaturu
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("radi");
			}
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					//System.out.println("radi");
					if(scena.radi()) {
						scena.pauziraj();
						Graphics g = scena.getGraphics();
						g.setFont(new Font("SansSerif", Font.BOLD,50));
						g.drawString("PAUZA", scena.getWidth()/2-100, scena.getHeight()/2-100);
					}
					else {
						scena.nastavi();
					}
				} 
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					izgasiti();
				}
			}
		});
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				izgasiti();
			}
		});
		
		setVisible(true);
	}
	
	private void izgasiti() {
		scena.zaustavi();
		dispose();
	}
	

	public static void main(String[] args) {
		new Simulacija();
	}

}
