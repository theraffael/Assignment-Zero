package model;

import logic.Game;
import logic.HumanPlayer;
import logic.PlayerContext;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private static Game game;


    @Before// setup()
    public void before(){
        Board board = new Board(true);
    }

    @Test
    public void testBoard(){
        assertNull(board);
    }

    @Test
    public void testCheckersInitialSetup(){
        Board board = new Board(true);
        String targetBoard = "      a     b     c     d     e     f     g     h\n" +
                "  +-------------------------------------------------+\n" +
                "1 | [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] | 1\n" +
                "2 | [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] | 2\n" +
                "3 | [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] | 3\n" +
                "4 | [   ] [   ] [   ] [   ] [   ] [   ] [   ] [   ] | 4\n" +
                "5 | [   ] [   ] [   ] [   ] [   ] [   ] [   ] [   ] | 5\n" +
                "6 | [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] | 6\n" +
                "7 | [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] | 7\n" +
                "8 | [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] | 8\n" +
                "  +-------------------------------------------------+\n" +
                "      a     b     c     d     e     f     g     h\n";
        assertEquals(targetBoard, board.getBoardString());
        //System.out.println(board.getBoardString());
    }

    @Test
    public void testCheckAndCrown(){
        board = new Board(false);
        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);

        /*      Setup board for testing purposes:
                     0      1      2      3      4      5      6      7
                     a      b      c      d      e      f      g      h
                  +_______________________________________________________+

            0   1 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            1   2 |  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]

            2   3 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            3   4 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            5   6 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            6   7 |  [   ]  [   ]  [   ]  [   ]  [R_P]  [   ]  [   ]  [   ]

            7   8 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]
        */

        board.addPiece(new Checker(PlayerColor.RED), 4,6);
        board.addPiece(new Checker(PlayerColor.WHITE), 4,1);


        game = new Game(board, redPlayer, whitePlayer, new UI(false));

        Move m1 = new Move(4,6, 3,7);
        Move m2 = new Move(4,1, 5,0);

        ArrayList MoveOne = new ArrayList();
        ArrayList MoveTwo = new ArrayList();

        MoveOne.add(m1);
        MoveTwo.add(m2);

        game.newMove(MoveOne);
        game.newMove(MoveTwo);

        assertTrue(board.fieldContainsKing(3,7));
        assertTrue(board.fieldContainsKing(5,0));

    }

    @Test
    public void testAddPiece(){

    }

    @Test
    public void testRemovePiece(){

    }

    @Test
    public void testFieldContainsKing(){

    }

    @After //tearDown()
    public void after() {
        board = null;
        assertNull(board);
    }
}