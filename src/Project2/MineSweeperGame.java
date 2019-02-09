package Project2;

import java.util.Random;

// TODO: class javadoc comment
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
        this.calcAllCellMineCounts();
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
        this.calcAllCellMineCounts();
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
        if(checkValidRowCol(row, col)) {
            throw new IllegalArgumentException();
        }

        Cell selectedCell = this.board[row][col];

        // If flagged do nothing
        if(selectedCell.isFlagged()) {
            return;
        }

        this.exposeCell(selectedCell);

        // If the selected cell has no adjacent mines then start to
        // recursively expose cells with no no adjacent mines
        if(selectedCell.getMineCount() == 0) {

            for(int i = row-1; i < row+1; ++i) {
                for(int j = col-1; j < col+1; ++j) {

                    // If the cell[i,j] is on the board, select it
                    if(!this.checkValidRowCol(i, j)) {
                        this.select(i, j);
                    }
                }
            }
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
     * Calculates and sets the number of adjacent mines for all cells
     * on the board.
     *****************************************************************/
    private void calcAllCellMineCounts() {

        for(int i=0; i<this.board.length; ++i) {
            for(int j=0; j<this.board.length; ++j) {
                calcCellMineCount(i, j);
            }
        }
    }

    /******************************************************************
     * Calculates the number of adjacent cells with mines. The value
     * calculated is set in the cell at [row][col].
     *
     * @param row row of cell who's 'mineCount' is to be calculated.
     * @param col col of cell who's 'mineCount' is to be calculated.
     * @throws IllegalArgumentException if 'row' or 'col' is out of
     *          bounds as an index for board[][].
     *****************************************************************/
    private void calcCellMineCount(int row, int col) throws
            IllegalArgumentException {

        // Check for valid inputs
        if(checkValidRowCol(row, col)) {
            throw new IllegalArgumentException();
        }

        int mineCount = 0;

        for(int i = row-1; i < row+1; ++i) {
            for(int j = col-1; j < col+1; ++j) {

                // Don't check for cells outside limits of board
                if(i != this.board.length && j != this.board.length) {

                    // Don't count this cell even if it has a mine
                    if(i != row && j != col) {

                        // Count adjacent cell has a mine
                        if(this.board[i][j].isMine()) {
                            ++mineCount;
                        }
                    }
                }
            }
        }

        // Set number of adjacent mines for this cell
        this.board[row][col].setMineCount(mineCount);
    }

    /******************************************************************
     * Checks if a given row and column are in bounds of the board[][]
     * indexes.
     *
     * @param row value to be tested.
     * @param col value to be tested.
     * @return true if 'row' or 'col' is invalid, false if 'row' and
     *         'col' are valid.
     *****************************************************************/
    private boolean checkValidRowCol(int row, int col) {

        // Check if row and col are inbounds of board indexes
        if(row < 0 || row >= this.board.length) {
            return true;
        }
        else if(col < 0 || col >= this.board.length) {
            return true;
        }

        return false;
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
        if(checkValidRowCol(row, col)) {
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


















































