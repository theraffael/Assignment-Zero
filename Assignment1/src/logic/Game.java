package logic;
import model.Board;
import model.Player;
import model.Checker;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private int turnCounter = 0;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    public Game(Board board, Player redPlayer, Player whitePlayer) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }

    //executes a move
    // todo: 13.10.2021: check if move is valid
    public void newMove(int fromX, int fromY, int toX, int toY){

        /*  checking for validity    */
        //isMove(fromX, fromY, toX, toY);
        //isJump(fromX, fromY, toX, toY);

        Checker checker = board.removePiece(fromX, fromY);
        board.addPiece(checker, toX, toY);
        turnCounter++;
    }

    //checks if single move is legal
    public boolean isMove(int currentX, int currentY, int nextX, int nextY){
        // Coordinates of start square must be valid
        if ((currentX < 0) || (currentY < 0) || (currentX > 7) || (currentY > 7)){return false;}
        // Coordinates of target square must be valid
        if ((nextX < 0) || (nextY < 0) || (nextX > 7) || (nextY > 7)){return false;}

        // Start square cannot be empty
        if (board.board[currentX][currentY] == null) {return false;}
        // Target square must be empty
        if (board.board[nextX][nextY] != null) {return false;}

        if (getActivePlayer().getColor() == "R") {   //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.board[currentX][currentY].getColor() == "W") {return false;}

            // if checker is pawn, nextY must be 1 larger than currentY
            int distanceY = nextY - currentY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.board[currentX][currentY].isKing()){
                distanceY = Math.abs(distanceY);
            }

            if (distanceY == 1 && (Math.abs(currentX - nextX) == 1 )){
            return true;

            }
            else{return false;}
        }

        else {   //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.board[currentX][currentY].getColor() == "R") {return false;}

            // if checker is pawn, nextY must be 1 smaller than currentY
            int distanceY = currentY - nextY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.board[currentX][currentY].isKing()){
                distanceY = Math.abs(distanceY);
            }
            if (distanceY == 1 && (Math.abs(currentX - nextX) == 1 )){
                return true;
            }
            else{return false;}
        }
        //todo: 13.10.2021:further checks
    }
    //checks if a move is a jump/capture => if at least one jump on a single turn is available, at least one jump has to be taken.
    public boolean isJump(int currentX, int currentY, int nextX, int nextY){
        return true;
    }

    //checks whether the game is finished
    public boolean isFinished(){
        ArrayList checkers = getActivePlayer().getCheckers();
        for (int i = 0; i < checkers.size(); i++ ) {
            Checker c = (Checker)checkers.get(i);
            // if at least one checker is not captured, the game is not over yet
            // todo: at least one checker has to be able to move
            if (true/*!c.isCaptured()*/) {//todo: debug isCaptured()...
                return false;
            }
        }
        return true;
    }

    //returns the Player whose turn it is
    public Player getActivePlayer(){
        if (turnCounter % 2 == 0){
            return redPlayer;
        }
        else {
            return whitePlayer;
        }
    }

    //calculates all possible moves
    public void CalcPossibleMoves(){

    }

    public void increaseTurnCounter() {
        this.turnCounter++;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }
}
