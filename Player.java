public class Player {
    // This board is the board set up by the other player, not the board set up by this player
    private Board theirBoard;

    /**
     * Player set up, assigned their own board
     * @param other
     */
    public Player(Board other)
    {
        theirBoard = other;
    }



    /**
     *
     * @return true if a player has won
     */
    public boolean hasWon ()
    {
        if (theirBoard.getPlacedShips().size() <= 0)
        {


            return true;
        }
        else {


            return false;
        }
    }


}
