package logic;
import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsSingleJumpTest {
    private static Game game;
    private static Board board;

    @BeforeEach
    public void setUp(){
        board = new Board();
        Player redPlayer = new Player("R");
        Player whitePlayer = new Player("W");

/*      Setup board for testing purposes:
             0      1      2      3      4      5      6      7
             a      b      c      d      e      f      g      h
          +_______________________________________________________+

    0   1 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

    1   2 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [W_K]  [   ]

    2   3 |  [   ]  [   ]  [   ]  [R_P]  [   ]  [R_P]  [   ]  [   ]

    3   4 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [   ]  [   ]

    4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [R_P]  [   ]  [   ]

    5   6 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [W_P]  [   ]

    6   7 |  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]  [   ]

    7   8 |  [   ]  [   ]  [R_P]  [   ]  [   ]  [   ]  [   ]  [   ]
*/
        Checker king = new Checker("W");
        king.crown();
        board.addPiece(king, 6,1);
        board.addPiece(new Checker("W"), 2,3);
        board.addPiece(new Checker("W"), 2,5);
        board.addPiece(new Checker("W"), 6,5);
        board.addPiece(new Checker("W"), 5,6);

        board.addPiece(new Checker("R"), 3,2);
        board.addPiece(new Checker("R"), 5,2);
        board.addPiece(new Checker("R"), 5,4);
        board.addPiece(new Checker("R"), 2,7);

        game = new Game(board, redPlayer, whitePlayer);
    }


    @Test
    void testOnlyMoveInAllowedDirection() {
        // Red turn
        game.setTurnCounter(0);
        // Only move down with red checker
        Move m1 = new Move(5,4,7,6);
        Move m2 = new Move(5,2,7,0);

        assertTrue(game.isSingleJump(m1, board),"Jump from (5,4 to 7,6)  with red checker");
        assertFalse(game.isSingleJump(m2, board),"Jump from (5,2 to 7,8) with red checker");
    }


/*    @Test
    void testKingsCanMoveUpAndDown() {
        // White turn
        game.setTurnCounter(1);
        // Move up and down with white king
        assertTrue(game.isLegalJump(2,5,3,4),
                "Move up (2,5 to 3,4) with white checker");
        assertTrue(game.isLegalJump(2,5,1,6),
                "Move down (2,5 to 1,6) with white checker");
    }*/

    @Test
    void testOnlyMoveToEmptySquare() {
        // Red turn
        game.setTurnCounter(0);
        assertFalse(game.isSingleJump(new Move(7,4,5,6), board));
        assertTrue(game.isSingleJump(new Move(5,4,7,6), board));
    }


    @Test
    void testOnlyJumpIfOpponentIsThere() {
        // Red turn
        game.setTurnCounter(0);
        assertFalse(game.isSingleJump(new Move(5,4,3,6), board));
        assertTrue(game.isSingleJump(new Move(5,4,7,6), board));

        // White turn
        game.setTurnCounter(1);
        assertFalse(game.isSingleJump(new Move(5,6,7,4), board));
        assertTrue(game.isSingleJump(new Move(6,1,4,3), board));
    }


    @Test
    void testMoveToDiagonallyAdjacent() {
        // Red turn
        game.setTurnCounter(1);
        assertTrue(game.isSingleJump(new Move(6,1,4,3), board));
        assertFalse(game.isSingleJump(new Move(6,1,3,4), board));
    }

    @Test
    void testOnlyMoveCurrentPlayerCheckers() {
        // Red turn
        game.setTurnCounter(0);

        // Move up with white checker
        assertFalse(game.isSingleJump(new Move(2,3,4,1), board));

        // White turn
        game.setTurnCounter(1);
        // Move up with white checker
        assertFalse(game.isSingleJump(new Move(3,2,1,4), board));
    }


}