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

        // TODO: Check for valid inputs

        this.boardSize = boardSize;
        this.mineCount = mineCount;
        this.boardPanel = new JPanel();
        this.btnPanel = new JPanel();
        this.quitBtn = new JButton();
        this.resetBtn = new JButton();
        this.board = new JButton[boardSize][boardSize];
        this.game = new MineSweeperGame(boardSize, mineCount);

        // Set overall layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        // Make button array panel for mine cells and place "*" on mines cells
        for(int i = 0; i < this.boardSize; ++i) {
            for(int j = 0; j < this.boardSize; ++j) {
                this.board[i][j] = new JButton();
                this.board[i][j].setEnabled(true);

                // Attach mouse listener to button
                this.board[i][j].addMouseListener(new CellMouseAdapter());

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

        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);



            if(SwingUtilities.isRightMouseButton(e)) {
                System.out.println("right click");
            }
            else if(SwingUtilities.isLeftMouseButton(e)) {
                System.out.println("left click");
            }
        }
    }

}





















































