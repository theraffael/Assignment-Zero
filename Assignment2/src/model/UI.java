package model;

import logic.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    private Game game;
    private Board board;
    private String move;
    private boolean isInputCorrect;
    public static Scanner keyBoard = new Scanner(System.in);

    private static UI instance = new UI();

    private UI(){}

    public static UI getInstance(){
        return instance;
    }

    /*public UI(boolean isStartUp) {
        if(isStartUp){
            this.startUp();
        }
    }*/

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

    public void startUp(){
        String[] acceptedPlayerTypes = {"HumanPlayer", "RandomPlayer", "MinMaxPlayer"};
        List<String> acceptedPlayerTypesList = new ArrayList<>(Arrays.asList(acceptedPlayerTypes));

        System.out.println("Welcome to Checkers, please choose the following Player types: HumanPlayer, RandomPlayer, MinMaxPlayer");
        System.out.println("Please enter Player type for red checkers");
        boolean isPlayerTypeAccepted = false;
        String redPlayerType = null;
        while(!isPlayerTypeAccepted){
            redPlayerType = keyBoard.nextLine();
            if(acceptedPlayerTypesList.contains(redPlayerType)){
                isPlayerTypeAccepted = true;
            }
            else{
                System.out.println("Input not accepted. Please enter only the following Player types: HumanPlayer, RandomPlayer, MinMaxPlayer");
            }
        }
        PlayerStrategy playerType = convertToStrategy(redPlayerType);
        PlayerContext redPlayer = new PlayerContext(playerType, PlayerColor.RED);

        System.out.println("Please enter Player type for white checkers");
        boolean isWhitePlayerTypeAccepted = false;
        String whitePlayerType = null;
        while(!isWhitePlayerTypeAccepted){
            whitePlayerType = keyBoard.nextLine();
            if(acceptedPlayerTypesList.contains(whitePlayerType)){
                isWhitePlayerTypeAccepted = true;
            }
            else{
                System.out.println("Input not accepted. Please enter only the following Player types: HumanPlayer, RandomPlayer, MinMaxPlayer");
            }
        }
        PlayerStrategy wplayerType = convertToStrategy(whitePlayerType);
        PlayerContext whitePlayer = new PlayerContext(wplayerType, PlayerColor.WHITE);


        this.board = new Board(true);
        this.game = new Game(board, redPlayer, whitePlayer, this);
        this.display();
        game.runGame();
    }

    static PlayerStrategy convertToStrategy(String playerType) {
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
        String boardString = board.getBoardString();
        // Clear previous output from the terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(boardString);

    }
    static ArrayList<Move> convertInputToXY(String s){
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
    static boolean checkInputIsValid(String input){
        Pattern p = Pattern.compile("[a-z][1-8](x[a-z][1-8])+");
        String lowercase = input.toLowerCase(Locale.ROOT);
        Matcher m = p.matcher(lowercase);
        return m.matches();
    }
    public void outputMoveToConsole(Move aiPlayerMove){
        String letterFromCoordinate = getCharForNumber(aiPlayerMove.getFromX());
        int intFromCoordinate = aiPlayerMove.getFromY() + 1;

        String letterToCoordinate = getCharForNumber(aiPlayerMove.getToX());
        int intToCoordinate = aiPlayerMove.getToY() + 1;

        String moveType;
        if (aiPlayerMove.isMoveJump()){
            moveType = "Jump";
        }
        else{
            moveType = "Single Move";
        }

        String output = String.format("AI Player Move: From %s%s To %s%s; Move Type %s",letterFromCoordinate,intFromCoordinate,letterToCoordinate,intToCoordinate,moveType);
        System.out.println(output);
    }
    static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 'A')) : null;
    }

    public static String returnPlayerTurn(boolean isRed){
        if (isRed){
            return "Red Players Turn";
        }
        else{
            return "White Players Turn";
        }
    }
    public void printInvalidMoveFurtherJump(){
        System.out.println("Invalid move, the checker can jump further");
    }
    public void printInvalidMoveMandatoryJump(){
        System.out.println("Invalid move, there is a mandatory jump available");
    }
    public void printInvalidJump(){
        System.out.println("Invalid jump, please try again.");
    }
    public void gameFinishedNoMoreMoves(String player){
        System.out.println(player + " has no more possible moves left and loses this game");
    }
    public void gameFinishedNoMorePieces(String player){
        System.out.println(player + " has no more pieces left and loses this game");
    }
    public void displayAmountOfMoves(int turnCounter){
        String output = String.format("This game took %s moves to finish", turnCounter);
        System.out.println(output);
    }
}
