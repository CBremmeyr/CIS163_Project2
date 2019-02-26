package Project2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeperPanel extends JPanel {

    private int boardSize;
    private int mineCount;
    private JPanel boardPanel;
    private JPanel btnPanel;
    private JButton quitBtn;
    private JButton resetBtn;
    private JButton[][] board;
    private MineSweeperGame game;

    public MineSweeperPanel(int boardSize, int mineCount) throws
            IllegalArgumentException {

        try {
            this.game = new MineSweeperGame(boardSize, mineCount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

        this.boardSize = boardSize;
        this.mineCount = mineCount;
        this.boardPanel = new JPanel();
        this.btnPanel = new JPanel();
        this.quitBtn = new JButton();
        this.resetBtn = new JButton();
        this.board = new JButton[boardSize][boardSize];

        // Set overall layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        // Make button array panel for mine cells and place "*" on mines cells
        for(int i = 0; i < this.boardSize; ++i) {
            for(int j = 0; j < this.boardSize; ++j) {
                this.board[i][j] = new JButton();
                this.board[i][j].setEnabled(true);

                // Attach mouse listener to button
                this.board[i][j].addMouseListener(new CellMouseAdapter(
                        this.game.getCell(i, j), this.board[i][j]));

                if(game.getCell(i, j).isMine()) {
                    board[i][j].setText("*");
                }
                else {
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


    }

    private class CellMouseAdapter extends MouseAdapter {

        private Cell cell;
        private JButton button;

        public CellMouseAdapter(Cell c, JButton b) throws IllegalArgumentException {

            this.cell = c;
            this.button = b;
        }

        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            // Toggle flag on right-click
            if(SwingUtilities.isRightMouseButton(e)) {
                this.cell.setFlagged(!this.cell.isFlagged());

                // Update text to show if flagged
                if(this.cell.isFlagged()) {
                    this.button.setText("F");
                } else {
                    this.button.setText(" ");
                }

            }

            // TODO: respond to left-click
            else if(SwingUtilities.isLeftMouseButton(e)) {

            }
        }
    }

}





















































