/**
 * This class contains detailed information on each cell
 */
public class Cell {
    //

    //var for isHit
    private boolean isHit;
    //Var to check if its a ship
    private boolean isShip;


    /**
     * Cell constructor
      * @param isHit
     * @param isShip
     */
    public Cell(boolean isHit, boolean isShip)
    {
        this.isHit = isHit;
        this.isShip = isShip;
    }

    /**
     * get isShip
     * @return
     */
    public boolean getIsShip() {
        return isShip;
    }

    /**
     * gets IsHit
     * @return
     */
    public boolean getIsHit() {
        return isHit;
    }

    /**
     * sets a cell to be hit or not
     * @param hit boolean if it is hit
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }
}
