package Project2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class MineSweeperGUI {

    public static void main(String arg[]) {

        final int MAX_BOARD_SIZE = 100;

        JFrame gui = new JFrame();
        String boardSizeInput;
        String mineInput;
        int boardSize;
        int mineCount;

        // Get board size and mine count from user with jOptionPane
        boardSizeInput = JOptionPane.showInputDialog("Enter Board Size");
        mineInput = JOptionPane.showInputDialog("Enter Number of Mines");

        // TODO: move lines to after inputs are check if valid
        boardSize = Integer.parseInt(boardSizeInput);
        mineCount = Integer.parseInt(mineInput);

        // TODO: Check for valid board size and mine count
        while(boardSize > MAX_BOARD_SIZE) {
            boardSizeInput = JOptionPane.showInputDialog("Board size %i is too big\nEnter a new value");
        }
        while(boardSize <= 0) {
            
        }

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Mine Sweeper (May the clicks be with you");

        gui.setContentPane(new MineSweeperPanel(boardSize, mineCount));
        gui.pack();

        gui.setVisible(true);

    }

}
