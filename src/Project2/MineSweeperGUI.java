package Project2;
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
        boardSize = self.getPopupInputNum("Enter board size");

        // Check for valid board size, if invalid get new value
        while(boardSize > MAX_BOARD_SIZE || boardSize <= 0) {
            boardSize = self.getPopupInputNum("Invalid board size value");
        }

        // Get mineCount from user
        mineCount = self.getPopupInputNum("Enter total amount of mines");

        // Check for valid mine count, if invalid get new value
        while(mineCount <= 0 || mineCount >= boardSize * boardSize) {
            mineCount = self.getPopupInputNum("Invalid mine amount\nEnter a new amount");
        }

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Mine Sweeper (May the clicks be with you");

        gui.setContentPane(new MineSweeperPanel(boardSize, mineCount));
        gui.pack();

        gui.setVisible(true);

    }

    private int getPopupInputNum(String mesg) {
        this.boardSizeInput = JOptionPane.showInputDialog(mesg);
        return Integer.parseInt(this.boardSizeInput);
    }

}
