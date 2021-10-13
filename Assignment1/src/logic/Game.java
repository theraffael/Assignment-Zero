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

    //starts a new game
    public void newGame(){

    }

    //executes a move
    // todo: check if move is valid
    public void newMove(int fromX, int fromY, int toX, int toY){
        Checker checker = board.removePiece(fromX, fromY);
        board.addPiece(checker, toX,toY);
        turnCounter++;
    }

    //checks if move is legal
    public void isLegal(){

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
