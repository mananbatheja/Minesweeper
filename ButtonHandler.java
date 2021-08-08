package minesweeper;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import minesweeper.*;

class ButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
     
        for(int i = 0;i< gui.grid.length; i++){
            for(int j = 0;j < gui.grid.length;j++){
                if(source == gui.grid[i][j]){
                    System.out.println(i+ " " + j);
                    // do something with this 
                }
            }
        }
    }
}