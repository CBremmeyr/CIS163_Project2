package Project2;

public class MineSweeperPanel extends JPanel implements ActionListener{


    private JButton[][] board;
    private JButton quitButton;
    private JButton resetButton;
    private Cell iCell;
    private JButton quitButton;
    private MineSweeperGame game;


MineSweeperPanel (){
    game = new MineSweeperGame();
    JPanel panel = new JPanel;


    for(int row = 0; row< 10; row++)
    {
        for (int col = 0; col < 10; col++) {
            board[row][col] = new JButton("", emptyIcon);
            board[row][col].addActionListener(listener);
            panel.add(board[row][col]);
        }
    }
    emptyIcon = new ImageIcon(); //add on to this with book



    }



    private displayBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                iCell = game.getCell(row, col);
                if (iCell.isExposed())
                    board[row][col].setEnabled(false);
                else
                    board[row][col].setEnabled(true);
                if (iCell.isExposed() && getMineCount() > 0)
                    board[row][col].setText("" + getMineCount());
                else {
                    board[row][col].setText(" ");
                }
            }

        }
    }


    private class Buttonistener implements ActionListener(){

    private actionPreformed() {
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (board[row][col] == e.getSource())
                        game.select(row, col);
                }
            }
            if (quitButton == e.getSource()){

            }
            if (resetButton == e.getSource()){
                game.reset();
            }
        displayboard();

        if (game.getGameStatus() == GameStatus.Lost) {
            JOptionPane.showMessageDialog(null, "You Lose”);
        }
        if (game.getGameStatus() == GameStatus.Won){
            JOptionPane.showMessageDialog(null, "You Win”);
        }

    }
    }




}




