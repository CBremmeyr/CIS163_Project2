package Project2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class MineSweeperGUI {

    public static void main(String arg[]) {

//        final int WINDOW_WIDTH = 400;
//        final int WINDOW_HEIGHT = 1000;

        JFrame gui = new JFrame();
        int boardSize = 10;
        int mineCount = 10;

        // TODO: Get board size and mine count from user with jOptionPane

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Mine Sweeper (May the clicks be with you");

//        gui.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gui.setContentPane(new MineSweeperPanel(boardSize, mineCount));
        gui.pack();

        gui.setVisible(true);



    }

}
