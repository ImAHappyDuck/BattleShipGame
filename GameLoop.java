import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class GameLoop {
    //Sets up the gameBoard
    //Player one board
    private Board p1b = new Board();
    //Player Two board
    private Board p2b = new Board();
    //Player one
    private Player p1 = new Player(p1b);
    //Player two
    private Player p2 = new Player(p2b);

    /**
     * runGame: Runs the game class
     */
    public void runGame() throws Exception {

        System.out.println("Player one starts. Hand the computer to player one");
        System.out.println(p1b.shipLocationBoardToString());
        Scanner scn = new Scanner(System.in);



        HashMap<String, Integer> shipNames = new HashMap<>();
        shipNames.put("Carrier", 5);
        shipNames.put("Battleship", 4);
        shipNames.put("Submarine", 3);
        shipNames.put("Cruiser", 3);
        shipNames.put("Destroyer", 2);

        ArrayList<String> shipName = new ArrayList<>();
        shipName.add("Carrier");
        shipName.add("Battleship");
        shipName.add("Submarine");
        shipName.add("Cruiser");
        shipName.add("Destroyer");
        boolean reLoop = false;


        System.out.println("Ship set up for Player 1:");
        while (reLoop == false) {
            try {

            for (int i = 0; i < shipNames.size(); i++) {
                int row = 0;
                int column = 0;
                while (reLoop == false) {
                    try {
                        System.out.println("Enter the row# for your upper left point of the " + shipName.get(i) + " size of " + shipNames.get(shipName.get(i)));
                        row = scn.nextInt();
                        System.out.println("Enter the column# for your upper left point of the " + shipName.get(i) + " size of " + shipNames.get(shipName.get(i)));
                        column = scn.nextInt();
                        break;
                        //  reLoop = true;

                    } catch (Exception e) {
                        System.out.println("Invalid. Try Again.");
                        scn.next();

                    }
                }

                boolean isHorizontal = false;
                while (reLoop == false) {
                    try {
                        System.out.println("Type true if you want ship horizontal, false for vertical");
                        isHorizontal = scn.nextBoolean();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid. Try Again.");
                        scn.next();
                    }
                }
                Ship newShip = new Ship(shipName.get(i), shipNames.get(shipName.get(i)), isHorizontal);
                p1b.addShip(newShip, row, column);
                System.out.println(p1b.shipLocationBoardToString());

            }
            break;
            } catch(Exception f )
            {
                System.out.println(f.getMessage());
            }
        }


        System.out.println("Push any key to clear the window then hand computer to player 2");
        scn.next();
        for(int i = 0; i <21; i++){
            System.out.println();
            System.out.println("DONT SCROLL UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println(p2b.shipLocationBoardToString());

        System.out.println("Ship set up for Player 2:");
        while (reLoop == false) {
            try {

                for (int i = 0; i < shipNames.size(); i++) {
                    int row = 0;
                    int column = 0;
                    while (reLoop == false) {
                        try {
                            System.out.println("Enter the row# for your upper left point of the " + shipName.get(i) + " size of " + shipNames.get(shipName.get(i)));
                            row = scn.nextInt();
                            System.out.println("Enter the column# for your upper left point of the " + shipName.get(i) + " size of " + shipNames.get(shipName.get(i)));
                            column = scn.nextInt();
                            break;
                            //  reLoop = true;

                        } catch (Exception e) {
                            System.out.println("Invalid. Try Again.");
                            scn.next();

                        }
                    }

                    boolean isHorizontal = false;
                    while (reLoop == false) {
                        try {
                            System.out.println("Type true if you want ship horizontal, false for vertical");
                            isHorizontal = scn.nextBoolean();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid. Try Again.");
                            scn.next();
                        }
                    }
                    Ship newShip = new Ship(shipName.get(i), shipNames.get(shipName.get(i)), isHorizontal);
                    p2b.addShip(newShip, row, column);
                    System.out.println(p2b.shipLocationBoardToString());


                }
                break;
            } catch(Exception f )
            {
                System.out.println(f.getMessage());
            }
        }

        System.out.println("Push any key + enter to clear the window then hand computer to player 1");
        scn.next();
        for(int i = 0; i <21; i++){
            System.out.println();
            System.out.println("DON'T SCROLL UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        int currentPlayer = 1;
        while(!p1.hasWon() && !p2.hasWon()){
            if(currentPlayer == 1){
                System.out.println("PLAYER ONE:");
                System.out.println(p1b.shipLocationBoardToString());
                System.out.println(p2b.hitLocationToString());
               while (reLoop == false) {
                   try {
                       System.out.println("Enter the row of the cell you want to shoot (X mark is a hit, O mark is a miss");
                       int row = scn.nextInt();
                       System.out.println("Enter the Column of the cell you want to shoot");
                       int column = scn.nextInt();
                       p2b.markHit(row, column);
                       break;
                   }catch (Exception e)
                   {
                       System.out.println("Invalid Input. Please try again");
                scn.next();
                   }
               }

                System.out.println(p2b.hitLocationToString());
                p2b.isSunk();
                if (p2.hasWon())
                {
                    break;
                }
                currentPlayer = 2;
                System.out.println("Push any key to change turns and clear screen");
                scn.next();
                for(int i = 0; i <21; i++){
                    System.out.println();
                    System.out.println("DONT SCROLL UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////");
                }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

            }
            if(currentPlayer == 2){
                System.out.println("PLAYER Two:");
                System.out.println(p2b.shipLocationBoardToString());
                System.out.println(p1b.hitLocationToString());
               while (reLoop == false) {
                    try {
                        System.out.println("Enter the row of the cell you want to shoot (X mark is a hit, O mark is a miss");
                        int row = scn.nextInt();
                        System.out.println("Enter the column of the cell you want to shoot");
                        int column = scn.nextInt();
                        p1b.markHit(row, column);
                        break;
                    }catch (Exception e)
                    {
                        System.out.println("Invalid Input. Please try again");
                   scn.next();
                    }
                }


                System.out.println(p1b.hitLocationToString());
                p1b.isSunk();
                if (p1.hasWon())
                {
                    break;
                }
                currentPlayer = 1;
                System.out.println("Push any key to change turns and clear screen");
                scn.next();
                for(int i = 0; i <21; i++){
                    System.out.println();
                    System.out.println("DONT SCROLL UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////");
                }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

            }

        }
            if(p1.hasWon()){
                System.out.println("Player TWO is VICTORIOUS");
            }
            if(p2.hasWon()){
                System.out.println("Player ONE is VICTORIOUS");
        }





    }
}
