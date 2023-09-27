import java.util.Scanner;

public class Battleship {
    /**
     * runs battleship the game
     * @param args
     */
    public static void main(String[] args) throws Exception{
     Scanner sc = new Scanner(System.in);
     boolean reLoop = false;
     String s = "";
     while (!reLoop) {
         try {
             System.out.println("Do you want to play multi-player or single player? ");
             System.out.println("Enter 'm' for 2 player and 's' for single player ");
             s = sc.next();
         } catch (Exception e) {
             System.out.println("Invalid Input. Try again");
         }
         if (s.equals("m")) {
             reLoop = true;
             GameLoop battleship = new GameLoop();
             battleship.runGame();
         }
         if (s.equals("s")) {
             reLoop = true;
             SinglePlayerGameLoop game = new SinglePlayerGameLoop();
             game.gameLoop();
         } else {
             System.out.println("Invalid input. Try again");
         }
     }
    }
}