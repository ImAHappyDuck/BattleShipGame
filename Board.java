import java.util.ArrayList;
import java.util.HashMap;

/**
 * Each ship is broken down into it's specific index info
 * That is then stored in a map with a key unique to each index.
 * When constructing the board now, all info can be had by simply calling the map
 * with the key for each index
 */
public class Board {
//      0  1  2  3  4  5  6  7  8  9
//     +–––––––––––––––––––––––––––––+
//    0|__|__|__|__|__|__|__|__|__|__|
//    1|__|__|__|__|__|__|__|__|__|__|
//    2|__|__|__|__|__|__|__|__|__|__|
//    3|__|__|__|__|__|__|__|__|__|__|
//    4|__|__|__|__|__|__|__|__|__|__|
//    5|__|__|__|__|__|__|__|__|__|__|
//    6|__|__|__|__|__|__|__|__|__|__|
//    7|__|__|__|__|__|__|__|__|__|__|
//    8|__|__|__|__|__|__|__|__|__|__|
//    9|__|__|__|__|__|__|__|__|__|__|

    //Hash map that has a number key for a cell value
    private HashMap<Integer, Cell> gameBoard;
    //The number of rows
    private int NumRows = 10;
    //The number of columns
    private int NumCols = 10;
    //An array list of placed ships
    private ArrayList<Ship> placedShips = new ArrayList<>();

    /**
     * returns placedShips
     * @return
     */
    public ArrayList<Ship> getPlacedShips() {
        return placedShips;
    }

    /**
     * Initializes a game board and sets each cell key, and the value to null.
     */
    public Board() {
        gameBoard = new HashMap();
        for (int i = 0; i < NumRows; i++) {
            for (int j = 0; j < NumCols; j++) {
                gameBoard.put(findKey(i, j), null);
            }
        }
    }

    /**
     * finds the key for a cell value based on row and col
     * @param row
     * @param col
     * @return
     */
    public int findKey(int row, int col) {
        int counter = 0;
        counter = (row * 50) + (col * 2);
        return counter;
    }

    /**
     * get NumCols
     * @return
     */
    public int getNumCols() {
        return NumCols;
    }

    /**
     * get numRows
     * @return
     */
    public int getNumRows() {
        return NumRows;
    }

    /**
     * gets gameBoard
     * @return
     */
    public HashMap<Integer, Cell> getGameBoard() {
        return gameBoard;
    }

    /**
     * @param s   Ship you want to place
     * @param row upper left most y value you want to place the ship
     * @param col upper left most x value you want to place the ship
     * @return a boolean dictating if there is another ship already placed in its proposed path.
     */
    public boolean noOverlap(Ship s, int row, int col) {
        if (s.getIsHorizontal()) {
            for (int i = col; i < col + s.getCellsSize() - 1; i++) {
                if (gameBoard.get(findKey(row, i)) != null && gameBoard.get(findKey(i, col)).getIsShip()) {
                    return false;
                }
            }
        }
        if (!s.getIsHorizontal()) {
            for (int i = row; i < row + s.getCellsSize() - 1; i++)
            {
                if (gameBoard.get(findKey(i, col)) != null && gameBoard.get(findKey(i, col)).getIsShip()) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Finds any ships that are sunk and returns an announcement that it is
     *
     * @return a string with the name of the ship saying its sunk
     */
    public void isSunk() {


        int total = placedShips.size();
        int remaining;
        for (int i = 0; i < placedShips.size(); i++) {
            remaining = placedShips.get(i).getCellsSize();
            if (placedShips.get(i).getIsHorizontal()) {
                for (int j = placedShips.get(i).getCol(); j < placedShips.get(i).getCol() + placedShips.get(i).getCellsSize(); j++)// removed -1
                {
                    if (gameBoard.get(findKey(placedShips.get(i).getRow(), j)) == null) {
                        // avoids exception
                    } else if (!gameBoard.get(findKey(placedShips.get(i).getRow(), j)).getIsHit()) {
                        //isn't hit
                    } else if (gameBoard.get(findKey(placedShips.get(i).getRow(), j)).getIsHit()) {
                        remaining--;

                    }

                }
            }
            if (!placedShips.get(i).getIsHorizontal()) {
                for (int j = placedShips.get(i).getRow(); j < placedShips.get(i).getRow() + placedShips.get(i).getCellsSize(); j++)// removed -1
                {
                    if (gameBoard.get(findKey(j, placedShips.get(i).getCol())) == null) {// || !gameBoard.get(findKey(j, placedShips.get(i).getRow())).getIsHit() ) {
                    } else if (!gameBoard.get(findKey(j, placedShips.get(i).getCol())).getIsHit()) {
                        //Isn't sunk
                    }
//
                    else if (gameBoard.get(findKey(j, placedShips.get(i).getCol())).getIsHit()) {
                        remaining--;
                    }

                }
            }
            if (remaining <= 0) {
                System.out.println("YAYAYAYAYAYAYAYAYAYAYAYAYAYAY!!!!!!");
                System.out.println(placedShips.get(i).getName() + " is sunk.");
                placedShips.remove(i);
                i = -1;
            }

        }


    }


    /**
     * Marks a cell as being hit from a guess
     *
     * @param row y coord
     * @param col x coord
     */
    public void markHit(int row, int col) {
        if (gameBoard.get(findKey(row, col)) == null) {
            Cell c = new Cell(true, false);
            gameBoard.put(findKey(row, col), c);
        } else {
            gameBoard.get(findKey(row, col)).setHit(true);
        }
    }

    /**
     * Places a ship on the board, by putting ship idx's in the appropriate map buckets
     *
     * @param s   the ship you want to place
     * @param row upper left most y coord of ship location
     * @param col upper left most x coord of ship location on grid board
     */
    public void addShip(Ship s, int row, int col) throws Exception {
        int index = 0;
        //places vertical ships
        if (s.getIsHorizontal() == false && noOverlap(s, row, col) && (row + s.getCellsSize() - 1) < NumRows && row > -1)//removed -1
        {
            s.setCol(col);
            s.setRow(row);
            placedShips.add(s);
            index = 0;
            for (int i = row; i < row + s.getCellsSize(); i++)
            {
                gameBoard.put(findKey(i, col), s.getIdx(index));
                index++;

            }

        }
//places horizontal ships
        else if (s.getIsHorizontal() && noOverlap(s, row, col) && (col + s.getCellsSize() - 1) < NumCols && col > -1)//removed -1
        {
            s.setCol(col);
            s.setRow(row);
            placedShips.add(s);
            index = 0;
            for (int i = col; i < col + s.getCellsSize(); i++)
            {
                gameBoard.put(findKey(row, i), s.getIdx(index));
                index++;

            }

        } else {

//
            throw new Exception("Invalid position. Try again");

        }
    }


    /**
     * Prints the ShipLocation to the String in the form of a battleship board with Ships..
     * This is what the User is looking at.
     * @return String of position of ships
     *
     */
    public String shipLocationBoardToString() {
        //      0   1   2   3   4   5   6   7   8   9
        //     +–––––––--------––––––––––––––––––––––+
        //    0|__|__|__|__|__|__|__|__|__|__|
        //    1|__|__|__|__|__|__|__|__|__|__|
        //    2|__|__|__|__|__|__|__|__|__|__|
        //    3|__|__|__|__|__|__|__|__|__|__|
        //    4|__|__|__|__|__|__|__|__|__|__|
        //    5|__|__|__|__|__|__|__|__|__|__|
        //    6|__|__|__|__|__|__|__|__|__|__|
        //    7|__|__|__|__|__|__|__|__|__|__|
        //    8|__|__|__|__|__|__|__|__|__|__|
        //    9|__|__|__|__|__|__|__|__|__|__|
        String gb = "   0   1   2   3   4   5   6   7   8   9\n";
        for (int i = 0; i < NumRows; i++) {
            gb = gb + i;
            for (int j = 0; j < NumCols; j++) {
                gb = gb + "|_";
                if (gameBoard.get(findKey(i, j)) == null || !gameBoard.get((findKey(i, j))).getIsShip()) {
                    gb = gb + " _";
                } else {
                    gb = gb + "S_";
                }
            }
            gb = gb + "|\n";

        }


        return gb;
    }

    /**
     *Prints the hitLocation to the String in the form of a battleship board with hits and misses.
     *This is what the User is looking at.
     * @return String of battleship board
     */
    public String hitLocationToString() {
        //      0   1   2   3   4   5   6   7   8   9
        //     +–––––––--------––––––––––––––––––––––+
        //    0|__|__|__|__|__|__|__|__|__|__|
        //    1|__|__|__|__|__|__|__|__|__|__|
        //    2|__|__|__|__|__|__|__|__|__|__|
        //    3|__|__|__|__|__|__|__|__|__|__|
        //    4|__|__|__|__|__|__|__|__|__|__|
        //    5|__|__|__|__|__|__|__|__|__|__|
        //    6|__|__|__|__|__|__|__|__|__|__|
        //    7|__|__|__|__|__|__|__|__|__|__|
        //    8|__|__|__|__|__|__|__|__|__|__|
        //    9|__|__|__|__|__|__|__|__|__|__|
        String gb = "   0   1   2   3   4   5   6   7   8   9\n";
        for (int i = 0; i < NumRows; i++) {
            gb = gb + i;
            for (int j = 0; j < NumCols; j++) {
                gb = gb + "|_";
                if (gameBoard.get(findKey(i, j)) == null || !gameBoard.get(findKey(i, j)).getIsHit()) {//||gameBoard.get(findKey(i,j)).getIsHit()== null) {
                    gb = gb + " _";
                } else if (!gameBoard.get(findKey(i, j)).getIsShip()) {
                    gb = gb + "0_";
                } else {
                    gb = gb + "X_";
                }
            }
            gb = gb + "|\n";

        }
        return gb;
    }

}



