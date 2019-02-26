package Project2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**********************************************************************
 * Panel to contain Mine Sweeper clone and run related game logic.
 *
 * @author Corbin Bremmeyr
 * @author Michael James
 * @version 25 February 2019
 *********************************************************************/
public class MineSweeperPanel extends JPanel {

    /**
     * Size of the board, used for size of button array
     */
    private int boardSize;

    /**
     * Total number of mines on the board
     */
    private int mineCount;

    /**
     * Panel to hold array of buttons to be used as game board
     */
    private JPanel boardPanel;

    /**
     * Panel to hold everything else
     */
    private JPanel btnPanel;

    /**
     * Button to quit game
     */
    private JButton quitBtn;

    /**
     * Button to start new game
     */
    private JButton resetBtn;

    /**
     * Array of buttons that make up the mine cells
     */
    private JButton[][] board;

    /**
     * Instance of game logic class
     */
    private MineSweeperGame game;

    /******************************************************************
     * Constructor to setup game with passed size and mine count.
     *
     * @param boardSize number of cells along one edge of square board.
     * @param mineCount number of mines held in the board.
     * @throws IllegalArgumentException if boardSize is outside of
     *          range from 2 to max limit, inclusive, or mineCount is
     *          outside range of 1 to number of cells-1, inclusive.
     *****************************************************************/
    public MineSweeperPanel(int boardSize, int mineCount) throws
            IllegalArgumentException {

        super();

        try {
            this.game = new MineSweeperGame(boardSize, mineCount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

        // Assign parameter values to instance variables
        this.boardSize = boardSize;
        this.mineCount = mineCount;

        // Allocate memory for objects
        this.boardPanel = new JPanel();
        this.btnPanel = new JPanel();
        this.quitBtn = new JButton();
        this.resetBtn = new JButton();
        this.board = new JButton[boardSize][boardSize];

        // Set overall layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        // Make button array panel for mine cells and place "*" on mines cells
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                this.board[i][j] = new JButton();
                this.board[i][j].setEnabled(true);

                // Attach mouse listener to button
                this.board[i][j].addMouseListener(new CellMouseAdapter(
                        this.game.getCell(i, j),
                        this.board[i][j],
                        this.game,
                        this,
                        i, j));

                if (game.getCell(i, j).isMine()) {
                    board[i][j].setText("*");
                } else {
                    board[i][j].setText(" ");
                }

                boardPanel.add(this.board[i][j]);
            }
        }

        // Make quit/reset button panel
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        resetBtn.setText("Reset");
        quitBtn.setText("Quit");

        btnPanel.add(resetBtn);
        btnPanel.add(quitBtn);

        this.add(boardPanel);
        this.add(btnPanel);

        resetBtn.addActionListener(new ButtonListener());
        quitBtn.addActionListener(new ButtonListener());

    }

    public JButton[][] getBoard() {
        return board;
    }

    private class ButtonListener implements ActionListener{



        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitBtn){
                Runtime.getRuntime().exit(1);
            }
            if (e.getSource() == resetBtn){
                game.reset();
                for (int i = 0; i < boardSize; i++) {
                    for (int j = 0; j < boardSize; j++) {
                            board[i][j].setEnabled(true);
                        if (game.getCell(i, j).isMine()) {
                            board[i][j].setText("*");
                        } else {
                            board[i][j].setText(" ");
                        }

                        }
                    }
                }
            }
        }


    /******************************************************************
     * Mouse listener used to responed based on type of click on button
     *
     * @author Corbin Bremmeyr
     * @version 25 February 2019
     *****************************************************************/
    private class CellMouseAdapter extends MouseAdapter {

        /**
         * Game logic instance being used by panel class
         */
        private MineSweeperGame game;

        private MineSweeperPanel panel;

        /**
         * Cell for button that listener is attached to
         */
        private Cell cell;

        /**
         * Button listener is attached to
         */
        private JButton button;

        /**
         * x-coordinate for cell location
         */
        private int x;

        /**
         * y-coordinate for cell location
         */
        private int y;

        /**************************************************************
         * Constructor to set all needed values from parameters.
         *
         * @param c associated mine cell.
         * @param b associated button.
         * @param g game panel's game logic instance.
         * @param p GUI panel that implements this.
         * @param x x-coordinate location value.
         * @param y y-coordinate location value.
         * @throws IllegalArgumentException if x or y is negative or
         *          grater than or equal to board's size, or if any
         *          refrence is null.
         *************************************************************/
        public CellMouseAdapter(Cell c, JButton b, MineSweeperGame g,
                                MineSweeperPanel p, int x, int y)
                throws IllegalArgumentException {

            super();

            // Check if IllegalArgumentException needs to be thrown
            if (c == null || b == null || g == null) {
                throw new IllegalArgumentException();
            } else if (x < 0 || x >= MineSweeperGUI.MAX_BOARD_SIZE ||
                    y < 0 || y >= MineSweeperGUI.MAX_BOARD_SIZE) {
                throw new IllegalArgumentException();
            }

            // Assign verified values to associated instance variable
            this.panel = p;
            this.game = g;
            this.cell = c;
            this.button = b;
            this.x = x;
            this.y = y;
        }

        public void updateButton() {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (game.getCell(i, j).isExposed()) {
                        this.panel.getBoard()[i][j].setEnabled(false);
                        if(game.getCell(i,j).getMineCount()==0)
                        {
                            this.panel.getBoard()[i][j].setText(" ");
                        }
                        else {
                            this.panel.getBoard()[i][j].setText("" + game.getCell(i, j).getMineCount());
                        }
                    }
                }
            }
        }

        /**************************************************************
         * Override super method to respond to right/left click
         * differently.
         *
         * @param e is event that triggered method call.
         *************************************************************/
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            // Toggle flag on right-click
            if (SwingUtilities.isRightMouseButton(e)) {
                this.cell.setFlagged(!this.cell.isFlagged());

                // Update text to show if flagged
                if (this.cell.isFlagged()) {
                    this.button.setText("F");
                } else {
                    this.button.setText(" ");
                }

            }

            // TODO: Respond to left-click
            else if (SwingUtilities.isLeftMouseButton(e)) {

                if (!cell.isFlagged()) {
                    this.game.select(x, y);
                    this.button.setEnabled(false);
                   updateButton();
                }

            }
            if (game.getStatus()== GameStatus.Lost){
                JOptionPane.showMessageDialog(null, "GAME OVER YOU LOSE", "Game Status", JOptionPane.CLOSED_OPTION);
            }

                if (game.getStatus()== GameStatus.Won){
                    JOptionPane.showMessageDialog(null, "CONGRATS YOU WON", "Game Status", JOptionPane.CLOSED_OPTION);

                }
        }
    }
}



