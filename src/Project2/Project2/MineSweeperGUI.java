package Project2;

import javax.swing.*;

/**********************************************************************
 * GUI class to allow user to play a Mine Sweeper clone.
 *
 * @author Corbin Bremmeyr
 * @author Michael James
 * @version 25 February 2019
 *********************************************************************/
public class MineSweeperGUI {

    /** Max board size supported by GUI */
    public static final int MAX_BOARD_SIZE = 30;

    /** Sting that user enters when prompted for board size */
    private String boardSizeInput;

    /** Sting that user enters when prompted for mine count */
    private String mineInput;

    /** Size of the game board */
    private int boardSize;

    /** Mines on game board */
    private int mineCount;

    /******************************************************************
     * Game entry point for Mine Sweeper clone.
     *
     * @param arg not used, needed to be main().
     *****************************************************************/
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
        gui.setTitle("Mine Sweeper (May the clicks be with you)");

        gui.setContentPane(new MineSweeperPanel(self.boardSize,
                self.mineCount));
        gui.pack();

        gui.setVisible(true);

    }

    /******************************************************************
     * Gets boardSize value from user with JOptionPane. Does not return
     * until user enters a valid value.
     *
     * @throws ExitException if user presses cancel button on
     *          the JOptionPane.
     *****************************************************************/
    private void getBoardSize() throws ExitException {

        boolean valid = true;
        int inputNum = -1;

        do {
            String inputStr = JOptionPane.showInputDialog("Enter " +
                    "board size.");

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
            if(inputNum <= 0 ||
                    inputNum > MineSweeperGUI.MAX_BOARD_SIZE) {
                valid = false;
            }
        } while(!valid);

        this.boardSize = inputNum;
    }

    /******************************************************************
     * Uses JOptionPane to get mineCount value from user. Does not
     * return until a valid value as been entered.
     *
     * @throws ExitException if cancel button was pressed in
     *          the JOptionPane
     *****************************************************************/
    private void getMineCount() throws ExitException {

        boolean valid = true;
        int inputNum = -1;

        do {
            String inputStr = JOptionPane.showInputDialog("Enter " +
                    "number of mines.");

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
            if(inputNum <= 0 ||
                    inputNum >= this.boardSize * this.boardSize) {
                valid = false;
            }
        } while(!valid);

        this.mineCount = inputNum;
    }

}
