package model;

import logic.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    private Game game;
    private Board board;
    private String move;
    private boolean isInputCorrect;
    public static Scanner keyBoard = new Scanner(System.in);

    public UI(boolean isStartUp) {
        if(isStartUp){
            this.startUp();
        }
    }
    public ArrayList<Move> handleInput(){
        System.out.println("Player Turn: "+ game.getActivePlayer().toString());
        isInputCorrect = false;
        while (!isInputCorrect) {
            System.out.println("Please enter coordinate format in form of a1xb2, or a1xb2xc3 for a multi jump");
            move = keyBoard.nextLine();
            isInputCorrect = this.checkInputIsValid(move);
            if (!isInputCorrect) {
                System.out.println("Coordinate format is incorrect, please try again");
                break;
            }
        }
        return this.convertInputToXY(move);
    }

    private void startUp(){
        System.out.println("Welcome to Checkers, please choose the following Player types: HumanPlayer, SimpleAI, ChallengingAI");
        System.out.println("Please enter Player type for red checkers");
        String redPlayerType = keyBoard.nextLine();
        PlayerStrategy playerType = convertToStrategy(redPlayerType);
        PlayerContext redPlayer = new PlayerContext(playerType, PlayerColor.RED);

        System.out.println("Please enter Player type for white checkers");
        String whitePlayerType = keyBoard.nextLine();
        PlayerStrategy wplayerType = convertToStrategy(whitePlayerType);
        PlayerContext whitePlayer = new PlayerContext(wplayerType, PlayerColor.RED);


        this.board = new Board(true);
        this.game = new Game(board, redPlayer, whitePlayer, this);
        this.display();
        game.runGame();
    }
    private PlayerStrategy convertToStrategy(String playerType) {
        if (playerType.equals("HumanPlayer")) {
            return new HumanPlayer();
        }
        else if (playerType.equals("RandomPlayer")) {
            return new RandomPlayer();
        }
        else {
            return new MinMaxPlayer();
        }
    }
    public void display(){
        Checker[][] checkerBoard = board.getBoard();
        // Clear previous output from the terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Start printing board
        System.out.print("      a     b     c     d     e     f     g     h"+
                "\n  +-------------------------------------------------+\n");
        for(int j = 0; j < 8; j++)
        {
            // // TODO: 13.10.21 : Invert numbers?
            System.out.print(j+1+" | ");
            for(int i = 0; i < 8; i++)
            {
                if(checkerBoard[i][j] == null)
                {
                    System.out.print("[   ] ");
                }
                else {
                    System.out.print("[" + checkerBoard[i][j].toString()+ "] ");
                }
            }
            System.out.print("| "+ (j+1) + "\n");
        }
        System.out.println("  +-------------------------------------------------+");
        System.out.println("      a     b     c     d     e     f     g     h");
    }
    private ArrayList<Move> convertInputToXY(String s){
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
    private boolean checkInputIsValid(String input){
        Pattern p = Pattern.compile("[a-z][1-8](x[a-z][1-8])+");
        String lowercase = input.toLowerCase(Locale.ROOT);
        Matcher m = p.matcher(lowercase);
        return m.matches();
    }
}
