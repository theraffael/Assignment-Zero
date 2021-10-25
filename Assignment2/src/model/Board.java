package model;
import logic.Game;

import java.util.Arrays;

public class Board {
    private Checker[][] board = new Checker[8][8];
    private int checkerCount = 0;
    public void display(){
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

    public void addPiece(Object checker, int x, int y)
    {
        board[x][y] = (Checker)checker;
        ((Checker) checker).setxPos(x);
        ((Checker) checker).setyPos(y);
        checkerCount ++;
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

    public boolean fieldContainsCheckerColor(int x, int y, String color) {
        // avoid error if field is empty
        if (fieldIsEmpty(x, y)){return false;}
        return this.board[x][y].getColor() == color;
    }

    private Board(Checker[][] oldBoard){
        this.board = oldBoard;
    }

    public Board clone() {
        //Todo: Make copies of checkers as well!!
        return new Board(this.board);
    }

    public Board() {};

}


