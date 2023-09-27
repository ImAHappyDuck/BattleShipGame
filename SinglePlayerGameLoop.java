import java.util.Random;
import java.util.Scanner;

public class SinglePlayerGameLoop {
    Board b = new Board();
    Random r = new Random();
    Ship x;
    boolean boardFull = false;
    boolean reLoop = true;
    Scanner scn = new Scanner(System.in);
    int lastRow1 = 0;
    int lastRow2 = 0;
    int lastCol1 = 0;
    int lastCol2 = 0;



    /**
     * finds if you have killed all the ships
     * @return
     */
    /**
     * Finds out if the player has sucesfully killed all the cells
     * Also runs through and sets boardFull to true if there is a ship in every cell.
     * @return a boolean of true if has won.
     */
    public boolean hasWon ()
{
    int count = 0;
    for (int i = 0; i < 10 + 1; i ++) {
        for (int j = 0; j < 10 + 1; j++) {
            if (b.getGameBoard().get(b.findKey(i,j)) == null)
            {

            }
          else  if (b.getGameBoard().get(b.findKey(i,j)).getIsShip())
            {
                count++;

            }
        }
        if (count >99 )
        {
            boardFull = true;
        }

    }


    for (int i = 0; i < b.getNumCols(); i++)
    {
        for (int j = 0; j < b.getNumCols(); j++)
        {
            if (b.getGameBoard().get(b.findKey(i,j)) == null)
            {

            }
          else if (b.getGameBoard().get(b.findKey(i,j)).getIsShip() && !(b.getGameBoard().get(b.findKey(i,j)).getIsHit()))

            {
                return false;
            }
        }
    }
    return true;
}

    /**
     * Algorithm for determining where to hide the next cells.
     * Does it's best to space them out and avoid randomness
     * Adds 1-2 new ships to the board each time it runs
     */
    public void add2 ()
{

    Ship y = new Ship("y", 1, true);
    Ship z = new Ship("z", 1, true);
boolean hasWorked = false;
int laps = 0;
  while (!hasWorked)
  {

      try {
          int row = r.nextInt(10);
          int col = r.nextInt(10);
          if (row >0 && col > 0 )
          {
              for (int i = row -1; i < row + 1; i ++)
              {
                  for (int j = col - 1; j < col + 1; j ++)
                  {
                      if (b.getGameBoard().get(b.findKey(i,j)).getIsShip())
                      {
                          throw new Exception("too close");
                      }
                  }
              }
             b.addShip(y,row,col);
              lastRow1 = row;
              lastCol1 = col;


          row = r.nextInt(10);
           col = r.nextInt(10);

              for (int i = row -1; i < row + 1; i ++)
              {
                  for (int j = col - 1; i < col + 1; j ++)
                  {
                      if (b.getGameBoard().get(b.findKey(i,j)).getIsShip())
                      {
                          throw new Exception("too close");
                      }
                  }
              }
              b.addShip(z,row,col);
              lastRow2 = row;
              lastCol2 = col;
              hasWorked = true;
          }
          else{
              row = r.nextInt(10);
              col = r.nextInt(10);
              b.addShip(y, row, col);
              lastRow1 = row;
              lastCol1 = col;

              row = r.nextInt(10);
              col = r.nextInt(10);
              b.addShip(z, row, col);
              lastRow2 = row;
              lastCol2 = col;              hasWorked = true;
          }
          if (laps > 10000 && !hasWon())
          {
              b.addShip(z, r.nextInt(10), r.nextInt(10));
              hasWorked = true;
          }
//          else if (laps > 100000) {
//              boardFull = true;
//              break;
//          }

      }catch (Exception e)
      {
          laps++;
      }
  }
}

    /**
     * runs through the single player game until the player wins or loses.
     */
    public void gameLoop () throws InterruptedException {
        try {
            x = new Ship("x", 1, true);
            b.addShip(x, r.nextInt(10), r.nextInt(10));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("IMPORTANT!!!!");
        Thread.sleep(500);
        System.out.println("The rules are simple:\nevery time you miss, a new enemy cell appear in the board. \n" +
                "Kill them all before the board fills up.");
        Thread.sleep(1050);
        System.out.println("Ships can appear in cells you already guessed and missed too");
        Thread.sleep(1500);
        System.out.println("So be warned, if you haven't won by your second shot, you might want to quit while you're ahead");
        Thread.sleep(5000);
        while (!hasWon() && !boardFull)
        {
//
            while (reLoop) {
                int row = 0;
                int col = 0;
                try {
                    System.out.println(b.hitLocationToString());
                    //todo: Cheat board displayed below
                   System.out.println(b.shipLocationBoardToString());
                    System.out.println("Enter the row of the cell you want to shoot (X mark is a hit, O mark is a miss");
                     row = scn.nextInt();
                    System.out.println("Enter the Column of the cell you want to shoot");
                     col = scn.nextInt();
                    b.markHit(row, col);

                }catch (Exception e)
                {
                    System.out.println("Invalid Input. Please try again");
                    scn.next();
                }
                if (hasWon() || boardFull)
                {
                    break;
                }
               if (!hasWon() ||!b.getGameBoard().get(b.findKey(lastRow1,lastCol1)).getIsHit() && !b.getGameBoard().get(b.findKey(lastRow2,lastCol2)).getIsHit())
                {
                    add2();
                }
            }
            if (hasWon())
            {
                System.out.println("YAYAYAYAAYAYAYAYAYAY: YOU WON!!!!");

            }
            else{
                System.out.println("TOO BAD, SOO SAD. YOU SIR, HAVE LOST");
            }



        }
    }
}
