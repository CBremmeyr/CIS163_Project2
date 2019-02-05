package Project2;

public class MineSweeperGame {

    /** Max dimensions of the game board */
    private final int MAX_BOARD_SIZE = 255;

    /** Array of cells that makes up the game board */
    private Cell[][] board;

    /** Dimension of the board */
    private int boardSize;

    /** Current state of the game */
    private GameStatus status;

    /** Total number of mines on the board */
    private int totalMineCount;

    /******************************************************************
     * Default constructor that sets board size and mine count to 10
     * and allocate memory for instance objects.
     *****************************************************************/
    public MineSweeperGame() {

        this.boardSize = 10;
        this.totalMineCount = 10;
        this.status = GameStatus.NotOverYet;

        this.board = new Cell[this.boardSize][this.boardSize];

        for(int i=0; i<this.boardSize; ++i) {
            for(int j=0; j<this.boardSize; ++i) {

                this.board[i][j] = new Cell();
            }
        }
    }

    /******************************************************************
     * Constructor that sets board size and total ammount of mines to
     * be on the board. The game board is also allocated based on
     * boardSize param.
     *
     * @param boardSize dimension of the game board to be allocated.
     * @param totalMineCount total amount of mines to place on the
     *                       board.
     * @throws IllegalArgumentException if boardSize is negative or is
     *         higher than max board size. Or if mine count is negative
     *         or greater than one less than the amount of cells on the
     *         game board.
     *****************************************************************/
    public MineSweeperGame(int boardSize, int totalMineCount) throws
            IllegalArgumentException {

        // Check for valid inputs //
        // Board size should be positive and under max size
        if(boardSize < 0 || boardSize > MAX_BOARD_SIZE) {
            throw new IllegalArgumentException();
        }

        // Mine count should be positive and allow for at least
        // 1 non-mine cell
        else if(totalMineCount < 0 ||
                totalMineCount > boardSize * boardSize - 1) {
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

        this.status = GameStatus.NotOverYet;
    }
}
