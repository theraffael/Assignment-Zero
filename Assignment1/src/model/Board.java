package model;

public class Board {
    private Checker[][] board = new Checker[8][8];
    private int checkerCount = 0;
    public void display(){
        String  letterCoordinates = "abcd";
        String  horizontalBoarder = "+-------------------------------------------------+";

    }

    public void addPiece(Checker checker, int x, int y)
    {
        board[x][y] = checker;
        checkerCount ++;
    }


}
