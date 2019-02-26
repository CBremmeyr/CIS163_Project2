package Project2;
import org.omg.CORBA.UserException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class MineSweeperGUI {

    private final int MAX_BOARD_SIZE = 100;

    private String boardSizeInput;
    private String mineInput;
    private int boardSize;
    private int mineCount;

    public static void main(String arg[]) {

        MineSweeperGUI self = new MineSweeperGUI();
        JFrame gui = new JFrame();

        // Get board size from user with jOptionPane
        try {
            self.getBoardSize();
        } catch (ExitException e) {
            return;
        }

        // Get mineCount from user
        try {
            self.getMineCount();
        } catch (ExitException e) {
            return;
        }

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Mine Sweeper (May the clicks be with you");

        gui.setContentPane(new MineSweeperPanel(self.boardSize, self.mineCount));
        gui.pack();

        gui.setVisible(true);

    }

    private void getBoardSize() throws ExitException {

        boolean valid = true;
        int inputNum = -1;

        do {
            String inputStr = JOptionPane.showInputDialog("Enter board size.");

            if(inputStr == null) {
                throw new ExitException();
            }

            valid = true;

            try {
                inputNum = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                valid = false;
            }

            // Check for valid number
            if(inputNum <= 0 || inputNum > this.MAX_BOARD_SIZE) {
                valid = false;
            }
        } while(!valid);

        this.boardSize = inputNum;
    }

    private void getMineCount() throws ExitException {

        boolean valid = true;
        int inputNum = -1;

        do {
            String inputStr = JOptionPane.showInputDialog("Enter number of mines.");

            if(inputStr == null) {
                throw new ExitException();
            }

            valid = true;

            try {
                inputNum = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                valid = false;
            }

            // Check if valid number
            if(inputNum <= 0 || inputNum >= this.boardSize * this.boardSize) {
                valid = false;
            }
        } while(!valid);

        this.mineCount = inputNum;
    }

}
