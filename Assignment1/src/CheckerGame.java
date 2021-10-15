import logic.*;
import model.*;

import java.util.Locale;
import java.util.regex.*;
import java.util.Scanner;

public class CheckerGame {

    public static Board board = new Board();

    public static Player redPlayer = new Player("R");
    public static Player whitePlayer = new Player("W");
    public static Scanner keyBoard = new Scanner(System.in);

    public static void main(String[] args) {
        setBoard();
        runGame();
    }

    public static void runGame(){
        Game game = new Game(board, redPlayer, whitePlayer);
        while (!game.isFinished()){
            board.display();
            System.out.println("Player Turn: "+ game.getActivePlayer().getColorWord());
            String move = keyBoard.nextLine();
            boolean isInputCorrect = checkInputIsValid(move);
            // move red pawn at D3 to E4, no logic implemented yet
            game.newMove(3,2,4,3);
        }
    }

    public static boolean checkInputIsValid(String input){
        Pattern p = Pattern.compile("\\[[a-z][1-8]\\]x\\[[a-z][1-8]\\]");
        String lowercase = input.toLowerCase(Locale.ROOT);
        Matcher m = p.matcher(lowercase);
        return m.matches();
    }

    public static void setBoard()
    {
        int k = 0;
        //this for loop is to set the red piece on top of the board in the arrangement of classic checkers

        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 8; i+=2)
            {
                board.addPiece(redPlayer.getCheckers().get(k), i + (j+1)%2, j);
                k++;
            }
        }
        k=0;
        //this for loop sets the white pieces on the bottom of the board in the arrangement of classic checkers
        for(int j = 5; j < 8; j++)
        {
            for(int i = 0; i < 8; i+=2)
            {
                board.addPiece(whitePlayer.getCheckers().get(k), i + (j+1)%2, j);
                k++;
            }
        }
        k=0;
    }
}
