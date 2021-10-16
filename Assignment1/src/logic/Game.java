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

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    private boolean isMove;

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
        String moveType = checkIfSingleOrJump(fromX, fromY, toX, toY);
        if (moveType == "Single") {
            isMove = this.isMove(fromX, fromY, toX, toY);
            if (isMove) {
                Checker checker = board.removePiece(fromX, fromY);
                board.addPiece(checker, toX, toY);
                turnCounter++;
                board.display();
                System.out.println("Player Turn: " + this.getActivePlayer().getColorWord());
            }
            else{
                System.out.println("Move is invalid, please try again");
            }
        }
        // todo: check if jump possible
        else if(moveType == "Jump"){
            isMove = this.isLegalJump(fromX, fromY, toX, toY);
            if (isMove) {
                //add and remove moving checker
                Checker checker = board.removePiece(fromX, fromY);
                board.addPiece(checker, toX, toY);
                //remove captured checker
                int x = Math.abs(fromX-toX);
                int y = Math.abs(fromY-toY);
                board.removePiece(x, y);
                turnCounter++;
                board.display();
                System.out.println("Player Turn: " + this.getActivePlayer().getColorWord());
            }
            else{
                System.out.println("Move is invalid, please try again");
            }
        }

        else {
            System.out.println("Move is invalid, please try again");
        }
    }

    //checks if single move is legal
    public boolean isMove(int currentX, int currentY, int nextX, int nextY){
        // Coordinates of start square must be valid
        if ((currentX < 0) || (currentY < 0) || (currentX > 7) || (currentY > 7)){return false;}
        // Coordinates of target square must be valid
        if ((nextX < 0) || (nextY < 0) || (nextX > 7) || (nextY > 7)){return false;}

        // Start square cannot be empty
        if (board.getBoard()[currentX][currentY] == null) {return false;}
        // Target square must be empty
        if (board.getBoard()[nextX][nextY] != null) {return false;}

        if (getActivePlayer().getColor() == "R") {   //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.getBoard()[currentX][currentY].getColor() == "W") {return false;}

            // if checker is pawn, nextY must be 1 larger than currentY
            int distanceY = nextY - currentY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.getBoard()[currentX][currentY].isKing()){
                distanceY = Math.abs(distanceY);
            }

            if (distanceY == 1 && (Math.abs(currentX - nextX) == 1 )){
            return true;

            }
            else{return false;}
        }

        else {   //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.getBoard()[currentX][currentY].getColor() == "R") {return false;}

            // if checker is pawn, nextY must be 1 smaller than currentY
            int distanceY = currentY - nextY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.getBoard()[currentX][currentY].isKing()){
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

    public boolean isLegalJump(int currentX, int currentY, int nextX, int nextY){


    // Start square cannot be empty
        if (board.getBoard()[currentX][currentY] == null) {return false;}
    // Target square must be empty
        if (board.getBoard()[nextX][nextY] != null) {return false;}

        if (getActivePlayer().getColor() == "R") {   //when it is red player's turn
        // Return false if checker belongs to white player
            if (board.getBoard()[currentX][currentY].getColor() == "W") {return false;}

            // if checker is pawn, nextY must be 2 larger than currentY (because of the jump)
            int distanceY = nextY - currentY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.getBoard()[currentX][currentY].isKing()) {
                distanceY = Math.abs(distanceY);
            }

            // return false if jumping over own checker red player over red checker
            int opponentX = (currentX + nextX) / 2;
            int opponentY = (currentY + nextY) / 2;
            if (board.getBoard()[opponentX][opponentY].getColor() == "R") {
                return false;
            }

            if (distanceY == 2 && (Math.abs(currentX - nextX) == 2 )){
                return true;

            } else // white player's turn
            {
                // Return false if checker belongs to red player
                if (board.getBoard()[currentX][currentY].getColor() == "R") {return false;}
                // if checker is pawn, nextY must be 2 smaller than currentY
                distanceY = currentY - nextY;
                // if checker is king, take absolute difference between nextY and currentY
                if (board.getBoard()[currentX][currentY].isKing()){
                    distanceY = Math.abs(distanceY);
                }
                if (distanceY != 2 && (Math.abs(currentX - nextX) != 2 )){
                    return false;
                }
                // return false if jumping over own checker white player over white checker
                int opponentX = (currentX + nextX) / 2;
                int opponentY = (currentY + nextY) / 2;
                if (board.getBoard()[opponentX][opponentY].getColor() == "W"){
                    return false; //
                }
                else{return true;}
            }


        }

        return true;

    }

    public static String checkIfSingleOrJump(int fromX, int fromY, int toX, int toY){
        int x = Math.abs(fromX - toX);
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
        ArrayList checkers = getActivePlayer().getCheckers();
        for (int i = 0; i<checkers.size(); i++){
            Checker c = (Checker)checkers.get(i);
        }
    }

    public void increaseTurnCounter() {
        this.turnCounter++;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }
}
