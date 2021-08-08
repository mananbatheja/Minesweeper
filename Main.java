package minesweeper;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Board b=new Board();
		b.init();
		System.out.println("Enter x and y");
		Scanner sc=new Scanner(System.in);
		while(true) {
			int x,y;
			x=sc.nextInt();
			y=sc.nextInt();
			b.click(x,y);
		}
	}
}
