package model;

public class Board {
    private Checker[][] board = new Checker[8][8];
    private int checkerCount = 0;
    public void display(){
        System.out.println("       a      b      c      d      e      f      g      h"+
                "\n  +_______________________________________________________+\n");
        for(int j = 0; j < 8; j++)
        {

            System.out.print(j+" |  ");
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


}
