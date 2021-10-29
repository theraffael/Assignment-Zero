package model;

import logic.Game;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    private PlayerType redPlayerType;
    private PlayerType whitePlayerType;
    private Game game;
    private Board board;
    private String move;
    private boolean isInputCorrect;
    public static Scanner keyBoard = new Scanner(System.in);

    public UI(boolean isStartUp) {
        if(isStartUp){
            this.startUp();
        }
        this.handleInput();
    }
    private void handleInput(){
        System.out.println("Player Turn: "+ "white");
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
        ArrayList<Move> convertedMoves = this.convertInputToXY(move);
        game.runGame(convertedMoves);
    }

    private void startUp(){
        System.out.println("Welcome to Checkers, please choose the following Player types: HumanPlayer, SimpleAI, ChallengingAI");
        System.out.println("Please enter Player type for red checkers");
        String redPlayerType = keyBoard.nextLine();
        System.out.println("Please enter Player type for white checkers");
        String whitePlayerType = keyBoard.nextLine();
        Board board = new Board(true);
        Game game = new Game(board, convertToEnum(redPlayerType), convertToEnum(whitePlayerType), this.ui);
        this.display();
    }
    private PlayerType convertToEnum(String playerType) {
        switch (playerType) {
            case "HumanPlayer":
                return PlayerType.HumanPlayer;
            break;
            case "SimpleAI":
                return PlayerType.SimpleAI;
            break;
            case "HardAI":
                return PlayerType.HardAI;
            break;
        }
    }
    private void display(){
        Checker[][] board = this.board.getBoard();
        // Clear previous output from the terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Start printing board
        System.out.println("       a      b      c      d      e      f      g      h"+
                "\n  +_______________________________________________________+\n");
        for(int j = 0; j < 8; j++)
        {
            // // TODO: 13.10.21 : Invert numbers?
            System.out.print(j+1+" |  ");
            for(int i = 0; i < 8; i++)
            {
                if(board[i][j] == null)
                {
                    System.out.print("[   ]  ");
                }
                else {
                    System.out.print("[" + board[i][j].toString()+ "]  ");
                }
            }
            System.out.println("\n");
        }
        System.out.println("\n");
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
