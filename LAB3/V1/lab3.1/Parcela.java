package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Parcela extends Label{
	
	private char oznaka;
	private Color color;

	public Parcela(char oznaka, Color color){
		this.oznaka = oznaka;
		this.color = color;
		setFont(new Font(Font.SERIF,Font.BOLD, 14));
		setForeground(Color.WHITE);
		setBackground(color);
		setAlignment((int)Component.BOTTOM_ALIGNMENT);
		String text = oznaka + "";
		setText(text);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getParent().dispatchEvent(e);;
				
			}
		});
	}
	

	public void changeColor(Color c) { color = c; setBackground(c);}
	

}
