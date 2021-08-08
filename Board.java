package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import minesweeper.*;


public class Board {
	int SIZE_OF_GRID = 10;
	int NUM_MINES = 10;
	int currentHidden=SIZE_OF_GRID*SIZE_OF_GRID;
	int[][] visited = new int[SIZE_OF_GRID][SIZE_OF_GRID]; 
	
	public ArrayList<ArrayList <Cell>> board=new ArrayList<>();
	
	void init() {
		for(int i=0; i<SIZE_OF_GRID; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			
			for(int j = 0; j<SIZE_OF_GRID; j++) {
				Cell c = new Cell();
				row.add(c);
			}
			board.add(row);
		}
		for(int[] row: visited) {
			Arrays.fill(row, 0);
		}
		fillMines();
		prettyPrint(board);
	}
	
	void fillMines() {
		Random r=new Random();
		int k = NUM_MINES;
		while(k-- != 0) {
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			
			Cell c = board.get(x).get(y);
			if(c.minePresent) {
				k++;
			}
			board.get(x).get(y).minePresent = true;
		}
		setNumberOfNeighbours();
	}
	int countMines(int x, int y) {
		int m = 0;
		if (!(x-1<0 || x-1>=SIZE_OF_GRID || y-1<0 || y-1>=SIZE_OF_GRID)&&this.getCell(x-1, y-1).minePresent) m++;
		if (!(x-1<0 || x-1>=SIZE_OF_GRID || y<0 || y>=SIZE_OF_GRID)&&this.getCell(x-1, y).minePresent) m++;
		if (!(x-1<0 || x-1>=SIZE_OF_GRID || y+1<0 || y+1>=SIZE_OF_GRID)&&this.getCell(x-1, y+1).minePresent) m++;
		if (!(x<0 || x>=SIZE_OF_GRID || y-1<0 || y-1>=SIZE_OF_GRID)&&this.getCell(x, y-1).minePresent) m++;
		if (!(x<0 || x>=SIZE_OF_GRID || y+1<0 || y+1>=SIZE_OF_GRID)&&this.getCell(x, y+1).minePresent) m++;
		if (!(x+1<0 || x+1>=SIZE_OF_GRID || y-1<0 || y-1>=SIZE_OF_GRID)&&this.getCell(x+1, y-1).minePresent) m++;
		if (!(x+1<0 || x+1>=SIZE_OF_GRID || y<0 || y>=SIZE_OF_GRID)&&this.getCell(x+1, y).minePresent) m++;
		if (!(x+1<0 || x+1>=SIZE_OF_GRID || y+1<0 || y+1>=SIZE_OF_GRID)&&this.getCell(x+1, y+1).minePresent) m++;
		return m;
	}

	Cell getCell(int x, int y) {
		return this.board.get(x).get(y);
	}
	
	void setNumberOfNeighbours() {
		for (int i = 0; i <SIZE_OF_GRID; i++) {
			for (int j = 0; j < SIZE_OF_GRID; j++) {
				int m;
				if(this.getCell(i, j).minePresent) {
					m = -1;
				}
				else{
					m = countMines(i, j);
				}
				this.getCell(i, j).nbrMines = m;
				
			}
		}
	}
	void gameOver() {
		System.out.println("GAME OVER!!");
		System.exit(0);
	}
	
	void gameWon() {
		System.out.println("GAME WON!!");
		prettyPrint(board);
		
		System.exit(0);
	}
	
	void click(int x, int y) {
		Cell c = this.getCell(x,y);
		if(c.minePresent) {
			gameOver();
		}
		refreshBoard(x,y);
		for(int[] row : visited) {
			Arrays.fill(row, 0);
		}
		prettyPrint(board);
	}
	
	void refreshBoard(int x, int y) {
		if(x<0 || x>=SIZE_OF_GRID || y<0 || y>=SIZE_OF_GRID || visited[x][y]==1) {
			return;
		}
		visited[x][y] = 1;
		if(this.getCell(x,y).minePresent) {
			// do nothing, dont unhide this cell;
			return;
		}
		else if(this.getCell(x, y).nbrMines==0){
			refreshBoard(x-1, y+1);
			refreshBoard(x, y+1);
			refreshBoard(x+1, y+1);
			refreshBoard(x+1, y);
			refreshBoard(x-1, y);
			refreshBoard(x+1, y-1);
			refreshBoard(x, y-1);
			refreshBoard(x-1, y-1);
		}
		this.getCell(x, y).hidden=false;
		currentHidden--;
		if(currentHidden==10) {
			gameWon();
		}
	}
	
	void printLine() {
		System.out.print("+");
		for( int i=0;i<10;i++) {
			System.out.print("---+");
		}
		System.out.print("\n");
	}
	void printRow(ArrayList <Cell> row, int j) {
		System.out.print("|");
		//String a = "\u00b7";
		String a = "\u2022";
		for(int i=0;i<10;i++) {
			if(row.get(i).hidden) {
				System.out.print(" "+a+" |");
			}
			else {
				System.out.print(" "+ (row.get(i).nbrMines == 0 ? " " : row.get(i).nbrMines)+" |");
			}
		}
		System.out.println(j + "");
		
	} 
	public void prettyPrint(ArrayList<ArrayList <Cell>> grid) {
		printLine();
		for( int i=0;i<10;i++) {
			printRow(grid.get(i), i);
			printLine();
		}
		System.out.print(" ");
		
		for ( int i = 0 ; i < 10 ; i++)
		{
			System.out.print("  " + i + " ");
		}
		System.out.println();
	}

}
