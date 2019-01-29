package Project2;

public class MineSweeperGame {

    /**  */
    private final int MAX_BOARD_SIZE = 255;

    /**  */
    private Cell[][] board;

    /**  */
    private int boardSize;

    /**  */
    private GameStatus status;

    /**  */
    private int totalMineCount;



    public MineSweeperGame(int boardSize, int totalMineCount) throws IllegalArgumentException {

        // Check for valid inputs //
        // Board size should be positive and under max size
        if(boardSize < 0 || boardSize > MAX_BOARD_SIZE) {
            throw new IllegalArgumentException();
        }

        // Mine count should be positive and allow for at least 1 non-mine cell
        else if(totalMineCount < 0 || totalMineCount > boardSize * boardSize - 1) {
            throw new IllegalArgumentException();
        }

        // Set instance variables to parameter values
        this.totalMineCount = totalMineCount;
        this.boardSize = boardSize;

        // Allocate memory for board and it's cells
        this.board = new Cell[boardSize][boardSize];

        for(int i=0; i<this.boardSize; ++i) {
            for(int j=0; j<this.boardSize; ++j) {

                this.board[i][j] = new Cell();
            }
        }
    }

    
}
