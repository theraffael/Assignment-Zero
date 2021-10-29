package model;
import logic.Game;

import java.util.Arrays;

public class Board {
    private Checker[][] board = new Checker[8][8];

    public Board(boolean isSetupBoard){
        if (isSetupBoard) {
            this.placeCheckersInitialSetup();
        }
    }

    public void placeCheckersInitialSetup()
    {
        //this for loop is to set the red piece on top of the board in the arrangement of classic checkers

        for(int j = 0; j < 3; j++) {
            for (int i = 0; i < 8; i += 2) {
                addPiece(new Checker(PlayerColor.RED), i + (j + 1) % 2, j);
            }
        }

        //this for loop sets the white pieces on the bottom of the board in the arrangement of classic checkers
        for(int j = 5; j < 8; j++)
        {
            for(int i = 0; i < 8; i+=2)
            {
                addPiece(new Checker(PlayerColor.WHITE), i + (j+1)%2, j);
            }
        }
    }

    public void addPiece(Object checker, int x, int y)
    {
        board[x][y] = (Checker)checker;
    }

    public Checker removePiece(int x, int y)
    {
        Checker toRemove = board[x][y];
        board[x][y] = null;
        return toRemove;
    }

    public boolean fieldIsEmpty(int x, int y) {
        return this.board[x][y] == null;
    }

    public boolean fieldContainsKing(int x, int y) {
        // avoid error if field is empty
        if (fieldIsEmpty(x, y)){return false;}
        return this.board[x][y].isKing();
    }

    public boolean fieldContainsCheckerColor(int x, int y, PlayerColor color) {
        // avoid error if field is empty
        if (fieldIsEmpty(x, y)){return false;}
        return this.board[x][y].playerColor().equals(color);
    }

    public Checker[][] getBoard(){
        Checker[][] boardClone = board.clone();
        return boardClone;
    }

    // Copy board state to new board object
    public Board(Board oldBoard) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (!oldBoard.fieldIsEmpty(x, y)) {
                    this.addPiece(new Checker(oldBoard.board[x][y]), x, y);
                }
            }
        }
    }
}


