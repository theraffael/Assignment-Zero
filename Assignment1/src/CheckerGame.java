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
        board.display();
        System.out.println("Player Turn: "+ game.getActivePlayer().getColorWord());
        while (!game.isFinished()){
            boolean isInputCorrect = false;
            boolean isMove = false;
            String move = null;

            while(!isInputCorrect && !isMove){
                System.out.println("Please enter coordinate format in form of [a1]x[b2]");
                move = keyBoard.nextLine();
                isInputCorrect = checkInputIsValid(move);
                if (!isInputCorrect){
                    System.out.println("Coordinate format is incorrect, please try again");
                    break;
                }
                // check if single move possible
                String[] convertedMove = convertInputToXY(move);
                String moveType = checkIfSingleOrJump(convertedMove);
                if (moveType == "Single") {
                    isMove = game.isMove(Integer.parseInt(convertedMove[0]) - 1, Integer.parseInt(convertedMove[1]) - 1, Integer.parseInt(convertedMove[2]) - 1, Integer.parseInt(convertedMove[3]) - 1);
                    if (isMove) {
                        game.newMove(Integer.parseInt(convertedMove[0]) - 1, Integer.parseInt(convertedMove[1]) - 1, Integer.parseInt(convertedMove[2]) - 1, Integer.parseInt(convertedMove[3]) - 1);
                        board.display();
                        System.out.println("Player Turn: " + game.getActivePlayer().getColorWord());
                        break;
                    }
                    else{
                        System.out.println("Move is invalid, please try again");
                        break;
                    }
                }
                // todo: check if jump possible
                else if(moveType == "Jump"){}

                else {
                    System.out.println("Move is invalid, please try again");
                    break;
                }
            }
        }
    }

    public static String checkIfSingleOrJump(String[] convertedMove){
        int x = Math.abs(Integer.parseInt(convertedMove[0]) - Integer.parseInt(convertedMove[2]));
        if (x == 1){
            return "Single";
        }
        if (x==2){
            return "Jump";
        }
        else{
            return "";
        }
    }

    public static String[] convertInputToXY(String s){
        s = s.replaceAll("[\\[x\\]]","").toLowerCase(Locale.ROOT);
        String newstring = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) {
                int pos = ch - 'a' + 1;
                newstring = newstring + pos;
            } else {
                newstring = newstring + ch;
            }
        }
        return newstring.split("");
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
