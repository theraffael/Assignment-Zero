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
        setBoard();
        runGame();
    }

    public static void runGame(){
        Game game = new Game(board, redPlayer, whitePlayer);
        board.display();
        System.out.println("Player Turn: "+ game.getActivePlayer().getColorWord());
        while (!game.isFinished()){
            boolean isInputCorrect = false;
            String move = null;
            game.setValidMove(false);
            while(!isInputCorrect && !game.isValidMove()){
                System.out.println("Please enter coordinate format in form of a1xb2");
                move = keyBoard.nextLine();
                isInputCorrect = checkInputIsValid(move);
                if (!isInputCorrect){
                    System.out.println("Coordinate format is incorrect, please try again");
                    break;
                }

                ArrayList<List<Integer>> convertedMoves = convertInputToXY(move);
                System.out.println(convertedMoves);
                game.newMove(convertedMoves);

            }
        }
    }


    public static ArrayList<List<Integer>> convertInputToXY(String s){
        String[] moves = s.toLowerCase(Locale.ROOT).split("x");
        ArrayList<List<Integer>> allMoves = new ArrayList();
        // allow for multi jump moves
        for (int i = 0; i < moves.length-1; i++) {
            String current = moves[i];
            String next = moves[i+1];

            int currentX = current.charAt(0) - 'a';
            int currentY = Integer.parseInt(current.substring(1))-1;
            int nextX = next.charAt(0) - 'a';
            int nextY = Integer.parseInt(next.substring(1)) -1;

            List<Integer> newMove = Arrays.asList(currentX, currentY, nextX, nextY);
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

    public static void setBoard()
    {
        int k = 0;
        //this for loop is to set the red piece on top of the board in the arrangement of classic checkers

        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 8; i+=2)
            {
                board.addPiece(redPlayer.getCheckers().get(k), i + (j+1)%2, j);
                redPlayer.setCheckerPosition(i+(j+1)%2,j);
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
                whitePlayer.setCheckerPosition(i+(j+1)%2,j);
                k++;
            }
        }
        k=0;

    }
}
