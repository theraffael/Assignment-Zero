package model;

import logic.Game;
import logic.HumanPlayer;
import logic.PlayerContext;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private static Game game;
    private PlayerContext redPlayer;
    private PlayerContext whitePlayer;

    @BeforeClass //gets executed once before all tests
    public static void beforeClass(){   }

    @Before// setup() ; gets executed before each test
    public void before(){
        Board board = new Board(true);
        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
    }

    @Test
    public void testBoard(){assertNull(board);  }

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
        //PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        //PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);

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


        game = new Game(board, redPlayer, whitePlayer, UI.getInstance());

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
        board = new Board(false);

        /*      Setup board for testing purposes:
                     0      1      2      3      4      5      6      7
                     a      b      c      d      e      f      g      h
                  +_______________________________________________________+

            0   1 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            1   2 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            2   3 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            3   4 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            5   6 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            6   7 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            7   8 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]
        */
        board.addPiece(new Checker(PlayerColor.RED), 4,6);
        board.addPiece(new Checker(PlayerColor.WHITE), 4,1);

        assertFalse(board.fieldIsEmpty(4,6));
        assertFalse(board.fieldIsEmpty(4,1));
        assertFalse(board.fieldContainsKing(4,6));
        assertFalse(board.fieldContainsKing(4,1));

        assertTrue(board.fieldContainsCheckerColor(4,6,PlayerColor.RED));
        assertTrue(board.fieldContainsCheckerColor(4,1,PlayerColor.WHITE));

    }

    @Test
    public void testRemovePiece(){
        board = new Board(false);

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

        board.removePiece(4,6);
        board.removePiece(4,1);

        assertTrue(board.fieldIsEmpty(4,6));
        assertTrue(board.fieldIsEmpty(4,1));

    }

    @Test
    public void testFieldContainsKing(){
        board = new Board(false);

        /*      Setup board for testing purposes:
                     0      1      2      3      4      5      6      7
                     a      b      c      d      e      f      g      h
                  +_______________________________________________________+

            0   1 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            1   2 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]

            2   3 |  [   ]  [   ]  [   ]  [R_P]  [   ]  [R_P]  [   ]  [   ]

            3   4 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [   ]  [   ]

            4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [R_P]  [   ]  [R_P]

            5   6 |  [   ]  [   ]  [W_K]  [   ]  [   ]  [   ]  [W_P]  [   ]

            6   7 |  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]  [   ]

            7   8 |  [   ]  [   ]  [R_P]  [   ]  [   ]  [   ]  [   ]  [   ]
        */
        Checker king1 = new Checker(PlayerColor.WHITE);
        king1.crown();
        Checker king2 = new Checker(PlayerColor.RED);
        king2.crown();
        board.addPiece(new Checker(PlayerColor.WHITE), 6,1);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(king1, 2,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 6,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 5,6);

        board.addPiece(new Checker(PlayerColor.RED), 3,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(king2, 5,4);
        board.addPiece(new Checker(PlayerColor.RED), 7,4);
        board.addPiece(new Checker(PlayerColor.RED), 2,7);

        assertTrue(board.fieldContainsKing(2,5));
        assertTrue(board.fieldContainsKing(5,4));

        assertFalse(board.fieldContainsKing(3,2));
        assertFalse(board.fieldContainsKing(6,1));

    }

    @After //tearDown()
    public void after() {
        board = null;
        assertNull(board);
    }
}