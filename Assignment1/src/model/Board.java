package model;

import com.sun.tools.javac.comp.Check;

import java.util.Arrays;

public class Board{
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
        checkerCount ++;
    }

    public Checker removePiece(int x, int y)
    {
        Checker toRemove = board[x][y];
        board[x][y] = null;
        checkerCount--;
        return toRemove;
    }

    public Checker[][] getBoard() {
        return board;
    }

    public Board(Checker[][] oldBoard){
        this.board = oldBoard;
    }

    public Board() {};

}


