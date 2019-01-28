/** Cell.java */

package Project2;

public class Cell {

    /** Number of adjacent cells with mines */
    private int mineCount;

    /** Tell if player has flagged cell as a mine */
    private boolean isFlagged;

    /** Tell if cell's contents is shown */
    private boolean isExposed;

    /** Tell if cell holds a mine */
    private boolean isMine;


    public Cell() {

        this.mineCount = 0;
        this.isFlagged = false;
        this.isExposed = false;
        this.isMine = false;
    }


    public int getMineCount() {
        return mineCount;
    }


    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }


    public boolean isFlagged() {
        return isFlagged;
    }


    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }


    public boolean isExposed() {
        return isExposed;
    }


    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }


    public boolean isMine() {
        return isMine;
    }


    public void setMine(boolean mine) {
        isMine = mine;
    }
}
