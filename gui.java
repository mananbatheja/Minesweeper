package minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import minesweeper.*;

public final class gui{
	
	static Frame f = new Frame("Game");
	
	static Label l = new Label("Test");
	
	final static [][] grid = new Button[10][10]; 
	
	public static void main(String[] args) {
		init();
	}

	static void init() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});   
		f.setSize(300,300);
		f.setLayout(new GridLayout(0, 10));
		
		int k = 1;
		for(Button[] row : grid) {
			for(int i=0; i<10; i++) {
				Button b = new Button("-");
				b.addActionListener(new ButtonHandler());
				row[i] = b;
			}
			System.out.println(k);
		}
		for(int i = 0; i<10; i++) {
			for(int j=0; j<10; j++) {
				f.add(grid[i][j]);
//				System.out.println(i+ " " + j);
			}
		}
		f.setVisible(true);
	}
}
