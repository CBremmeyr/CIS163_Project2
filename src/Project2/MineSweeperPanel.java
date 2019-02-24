package Project2;

import javax.swing.*;

public class MineSweeperPanel extends JPanel {

    private int boardSize;
    private int mineCount;
    private JButton[][] board;
    private MineSweeperGame game;

    public MineSweeperPanel(int boardSize, int mineCount) throws IllegalArgumentException {

        // TODO: Check for valid inputs

        this.boardSize = boardSize;
        this.mineCount = mineCount;
        this.board = new JButton[boardSize][boardSize];
        this.game = new MineSweeperGame(boardSize, mineCount);

        // Make button array for mine cells
        for(int i = 0; i < this.boardSize; ++i) {
            for(int j = 0; j < this.boardSize; ++j) {
                this.board[i][j] = new JButton();
            }
        }
    }
}
