package Project2;

import java.util.Random;

public class MineSweeperGame {

    /** Max dimensions of the game board */
    private final int MAX_BOARD_SIZE = 255;

    /** Board size value used by default constructor */
    private final int DEFAULT_BOARD_SIZE = 10;

    /** Total mine count value used by default constructor */
    private final int DEFAULT_MINE_COUNT = 10;

    /** Array of cells that makes up the game board */
    private Cell[][] board;

    /** Dimension of the board */
    private int boardSize;

    /** Current state of the game */
    private GameStatus status;

    /** Total number of mines on the board */
    private int totalMineCount;

    /** Number of cells exposed, used to see if player won */
    private int exposedCount;

    /******************************************************************
     * Default constructor that sets board size and mine count to 10
     * and allocate memory for instance objects.
     *****************************************************************/
    public MineSweeperGame() {

        this.boardSize = DEFAULT_BOARD_SIZE;
        this.totalMineCount = DEFAULT_MINE_COUNT;
        this.status = GameStatus.NotOverYet;
        this.exposedCount = 0;

        this.allocateBoard();
        this.reset();
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

        // Check for valid inputs
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
        this.status = GameStatus.NotOverYet;
        this.exposedCount = 0;

        // Allocate memory for board and it's cells
        this.allocateBoard();
        this.reset();
    }

    /******************************************************************
     * Randomly fill board with mines. Values for the board size and
     * total mine count need to be set and memory for board cells must
     * be allocated before function is used.
     *
     * @throws NullPointerException if cells in board are null pointers
     * @throws IllegalArgumentException if mineCount or board size is
     *          not set.
     *****************************************************************/
    private void placeMines() throws NullPointerException,
            IllegalArgumentException {

        // Test if boardSize and mineCount is set
        if(this.boardSize == 0 || this.totalMineCount == 0) {
            throw new IllegalAccessError();
        }

        Random rn = new Random();
        int minesPlaced = 0;

        while(minesPlaced < this.totalMineCount) {

            int col = rn.nextInt(this.boardSize);
            int row = rn.nextInt(this.boardSize);

            try {
                if (!this.board[row][col].isMine()) {
                    this.board[row][col].setMine(true);
                }
            }
            catch (NullPointerException e) {
                throw new NullPointerException();
            }
        }
    }

    /******************************************************************
     * API function for when a user clicks a box.
     *
     * @param row row of the  button pressed.
     * @param col column of the button pressed.
     * @throws IllegalArgumentException if 'row' or 'col' is not
     *          between 0 and boardSize - 1
     *****************************************************************/
    public void select(int row, int col) throws IllegalArgumentException {

        // Check that row and column are in valid range
        if(row < this.boardSize || row >= this.boardSize) {
            throw new IllegalArgumentException();
        }
        else if(col < this.boardSize || col >= this.boardSize) {
            throw new IllegalArgumentException();
        }

        Cell selectedCell = this.board[row][col];

        // If flagged do nothing
        if(selectedCell.isFlagged()) {
            return;
        }
        else {
            exposeCell(selectedCell);
        }
    }

    /******************************************************************
     * Sets the board for a new game.
     *****************************************************************/
    public void reset() {

        // Reset each cell
        for(int i=0; i<this.boardSize; ++i) {
            for(int j=0; j<this.boardSize; ++i) {
                this.board[i][j].setExposed(false);
                this.board[i][j].setMine(false);
                this.board[i][j].setFlagged(false);
                this.board[i][j].setMineCount(0);
            }
        }

        this.placeMines();
        this.exposedCount = 0;
    }

    // TODO: finish function
    private void exposeCell(Cell selectedCell) {

        // If player clicked on a mine
        if(selectedCell.isMine()) {
            this.status = GameStatus.Lost;
        }
        else {

        }

        ++this.exposedCount;
    }

    /******************************************************************
     * Helper method for constructors that makes new objects for
     * 'board' and all its elements.
     *****************************************************************/
    private void allocateBoard() {

        // Make new array of Cells
        this.board = new Cell[boardSize][boardSize];

        // Fill array with new Cells
        for(int i=0; i<this.boardSize; ++i) {
            for(int j=0; j<this.boardSize; ++j) {

                this.board[i][j] = new Cell();
            }
        }
    }

    /******************************************************************
     * Getter for cell at location [row][col].
     *
     * @param row row of cell to get.
     * @param col column of cell to get.
     * @return cell at location [row][col]
     * @throws IllegalArgumentException if 'row' or 'col' are negative
     *          or greater/equal to the board size.
     *****************************************************************/
    public Cell getCell(int row, int col) throws
            IllegalArgumentException {

        // Check for valid row and column values
        if(row < 0 || row >= this.boardSize) {
            throw new IllegalArgumentException();
        }
        else if(col < 0 || col >= this.boardSize) {
            throw new IllegalArgumentException();
        }

        return this.board[row][col];
    }

    /******************************************************************
     * Gets the current status of the game.
     *
     * @return if the game has been lost, won, or ongoing.
     *****************************************************************/
    public GameStatus getStatus() {
        return status;
    }
}


















































