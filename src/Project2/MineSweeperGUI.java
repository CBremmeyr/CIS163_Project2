package Project2;
import org.omg.CORBA.UserException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class MineSweeperGUI {

    private String boardSizeInput;
    private String mineInput;

    public static void main(String arg[]) {

        final int MAX_BOARD_SIZE = 100;

        MineSweeperGUI self = new MineSweeperGUI();
        JFrame gui = new JFrame();
        int boardSize;
        int mineCount;

        // Get board size from user with jOptionPane
        try {
            boardSize = self.getPopupInputNum("Enter board size");
        } catch (ExitException e) {
            return;
        }

        // Check for valid board size, if invalid get new value
        while(boardSize > MAX_BOARD_SIZE || boardSize <= 0) {
            try {
                boardSize = self.getPopupInputNum("Invalid board " +
                        "size value\nEnter a new board size");

            } catch (ExitException e) {
                return;
            }
        }

        // Get mineCount from user
        try {
            mineCount = self.getPopupInputNum("Enter total amount of mines");
        } catch (ExitException e) {
            return;
        }

        // Check for valid mine count, if invalid get new value
        while(mineCount <= 0 || mineCount >= boardSize * boardSize) {
            try {
                mineCount = self.getPopupInputNum("Invalid mine amount\nEnter a new amount");
            } catch (ExitException e) {
                return;
            }
        }

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Mine Sweeper (May the clicks be with you");

        gui.setContentPane(new MineSweeperPanel(boardSize, mineCount));
        gui.pack();

        gui.setVisible(true);

    }

    private int getPopupInputNum(String mesg) throws ExitException {
        this.boardSizeInput = JOptionPane.showInputDialog(mesg);
        try {
            return Integer.parseInt(this.boardSizeInput);

        } catch (Exception e) {
            throw new ExitException();
        }
    }

}
