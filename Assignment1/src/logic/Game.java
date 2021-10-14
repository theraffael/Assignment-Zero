package logic;
import model.Board;
import model.Player;
import model.Checker;

import java.util.ArrayList;

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
        isLegal(fromX, fromY, toX, toY);
        Checker checker = board.removePiece(fromX, fromY);
        board.addPiece(checker, toX, toY);
        turnCounter++;
    }

    //checks if move is legal
    public boolean isLegal(int currentX, int currentY, int nextX, int nextY){
        //first check if current checker can be moved diagonally
        if (turnCounter % 2 == 0) {   //when it is red player's turn    //todo: 13.10.2021: instead of only checking one field diagonally, check for the specific move of the player
            if (board.getBoard()[currentX + 1][currentY + 1] == null){   //checks only to the bottom right
                return true;
            }
            else{return false;}
        }
        else {   //when it is white player's turn
            if (board.getBoard()[currentX-1][currentY - 1] == null){  //checks only to the upper left
                return true;
            }
            else{return false;}
        }
        //todo: 13.10.2021:further checks
    }

    //checks whether the game is finished
    public boolean isFinished(){
        ArrayList checkers = getActivePlayer().getCheckers();
        for (int i = 0; i < checkers.size(); i++ ) {
            Checker c = (Checker)checkers.get(i);
            // if at least one checker is not captured, the game is not over yet
            // todo: at least one checker has to be able to move
            if (!c.isCaptured()) {
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
}
