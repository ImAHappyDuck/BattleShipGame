import java.util.ArrayList;

/**
 * This class has the ships name and size, and an arraylist of an objects that has
 * specified info for each cell.
 */
public class Ship {
    //Name of the Ship
    private String name;
    //Boolean for if ship is horizontal
    private boolean isHorizontal;
    //Array List of Cells
    private ArrayList<Cell> cells;
    //rows
    private int row;
    //columns
    private int col;

    /**
     * gets the Row
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * gets the column
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * sets the column
      * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * sets the row
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Constructor for the ship
     * @param name name of ship
     * @param size size of the ship
     * @param isHorizontal if the ship is horizontal
     */
    //
    public Ship (String name, int size, boolean isHorizontal )
    {
        this.cells = new ArrayList<>();
        this.name = name;
        this.isHorizontal = isHorizontal;

        for (int i = 0; i < size; i++)
        {
            Cell e = new Cell(false,true);
            cells.add(e);
        }

    }

    /**
     * gets the Cell for a specific index
     * @param Index
     * @return
     */
    public Cell getIdx (int Index)
    {
        return cells.get(Index);
    }

    /**
     * gets is horizontal
     * @return
     */
    public boolean getIsHorizontal()
    {
        return  isHorizontal;
    }


    /**
     * gets the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * gets the cell size
     * @return
     */
    public int getCellsSize ()
    {
        return cells.size();
    }


}
