import logic.*;
import model.*;

import java.util.*;
import java.util.regex.*;

public class CheckerGame {


    public static Board board = new Board();

    public static Player redPlayer = new Player("R");
    public static Player whitePlayer = new Player("W");
    public static Scanner keyBoard = new Scanner(System.in);

    public static void main(String[] args) {
        board.placeCheckersInitialSetup();
        runGame();
    }

    public static void runGame(){
        Game game = new Game(board, redPlayer, whitePlayer);
        board.display();
        System.out.println("Player Turn: "+ game.getActivePlayer().getColorWord());

        while (!game.isFinished()){
            boolean isInputCorrect = false;
            boolean moveSuccessful = false;
            String move;

            while(!isInputCorrect && !moveSuccessful){
                System.out.println("Please enter coordinate format in form of a1xb2, or a1xb2xc3 for a multi jump");
                move = keyBoard.nextLine();
                isInputCorrect = checkInputIsValid(move);
                if (!isInputCorrect){
                    System.out.println("Coordinate format is incorrect, please try again");
                    break;
                }

                ArrayList<Move> convertedMoves = convertInputToXY(move);
                System.out.println(convertedMoves);
                moveSuccessful = game.newMove(convertedMoves);
                if(!moveSuccessful){
                    System.out.println("Incorrect move, please try again");
                }

            }
        }
    }


    public static ArrayList<Move> convertInputToXY(String s){
        String[] moves = s.toLowerCase(Locale.ROOT).split("x");
        ArrayList<Move> allMoves = new ArrayList();
        // allow for multi jump moves
        for (int i = 0; i < moves.length-1; i++) {
            String current = moves[i];
            String next = moves[i+1];

            int currentX = current.charAt(0) - 'a';
            int currentY = Integer.parseInt(current.substring(1))-1;
            int nextX = next.charAt(0) - 'a';
            int nextY = Integer.parseInt(next.substring(1)) -1;

            Move newMove = new Move(currentX, currentY, nextX, nextY);
            allMoves.add(newMove);
        }
        return allMoves;
    }
    public static boolean checkInputIsValid(String input){
        Pattern p = Pattern.compile("[a-z][1-8](x[a-z][1-8])+");
        String lowercase = input.toLowerCase(Locale.ROOT);
        Matcher m = p.matcher(lowercase);
        return m.matches();
    }

}
