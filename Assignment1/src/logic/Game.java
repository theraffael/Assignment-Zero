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

        if (turnCounter % 2 == 0) {   //when it is red player's turn
            if ( ( (board.getBoard()[currentX+1][currentY+1] == board.getBoard()[nextX][nextY]) || (board.getBoard()[currentX-1][currentY+1] == board.getBoard()[nextX][nextY]) ) //first checks if the input of the player is a move
                    &&
                    (board.getBoard()[nextX][nextY] == null)){//checks if the field is empty
                return true;
            }
            else{
                return false;
            }
        }
        else {   //when it is white player's turn
            if ( ( (board.getBoard()[currentX-1][currentY-1] == board.getBoard()[nextX][nextY]) || (board.getBoard()[currentX+1][currentY-1] == board.getBoard()[nextX][nextY]) ) //first checks if the input of the player is a move
                    &&
                    (board.getBoard()[nextX][nextY] == null)){//checks if the field is empty
                return true;
            }
            else{
                return false;
            }
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
