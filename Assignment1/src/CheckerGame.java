import logic.*;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckerGame {

    public static Board board = new Board();
    public static ArrayList redCheckers = new ArrayList();
    public static ArrayList blackCheckers = new ArrayList();
    public static Scanner keyBoard = new Scanner(System.in);


    public static void main(String[] args) {

        //fills the red and black lists with checker objects.
        for(int i = 0; i < 12; i++)
        {
            redCheckers.add(new Checker("r"));
            blackCheckers.add(new Checker("b"));
        }

        setBoard();
        board.display();
    }

    public static void setBoard()
    {
        int k = 0;
        //this for loop is to set the red piece on top of the board in the arrangement of classic checkers

        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 8; i+=2)
            {
                board.addPiece(redCheckers.get(k), i +j%2, j);
                k++;
            }
        }
        k=0;
        //this for loop sets the black pieces on the bottom of the board in the arrangement of classic checkers
        for(int j = 5; j < 8; j++)
        {
            for(int i = 0; i < 8; i+=2)
            {
                board.addPiece(blackCheckers.get(k), i +j%2, j);
                k++;
            }
        }
        k=0;
    }
}
