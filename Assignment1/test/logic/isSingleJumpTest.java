package logic;
import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsSingleJumpTest {
    private static Game game;

    @BeforeEach
    public void setUp(){
        Board board = new Board();
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

        board.addPiece(whitePlayer.getCheckers().get(0), 6,1);
        board.addPiece(whitePlayer.getCheckers().get(1), 2,3);
        board.addPiece(whitePlayer.getCheckers().get(2), 2,5);
        board.getBoard()[6][1].crown();
        board.addPiece(whitePlayer.getCheckers().get(3), 6,5);
        board.addPiece(whitePlayer.getCheckers().get(4), 5,6);

        board.addPiece(redPlayer.getCheckers().get(0), 3,2);
        board.addPiece(redPlayer.getCheckers().get(1), 5,2);
        board.addPiece(redPlayer.getCheckers().get(2), 5,4);
        board.addPiece(redPlayer.getCheckers().get(5), 2,7);

        game = new Game(board, redPlayer, whitePlayer);
    }


    @Test
    void testOnlyMoveInAllowedDirection() {
        // Red turn
        game.setTurnCounter(0);
        // Only move down with red checker
        assertTrue(game.isSingleJump(5,4,7,6),
                "Jump from (5,4 to 7,6)  with red checker");
        assertFalse(game.isSingleJump(5,2,7,0),
                "Jump from (5,2 to 7,8) with red checker");
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
        assertFalse(game.isSingleJump(7,4,5,6));
        assertTrue(game.isSingleJump(5,4,7,6));
    }


    @Test
    void testOnlyJumpIfOpponentIsThere() {
        // Red turn
        game.setTurnCounter(0);
        assertFalse(game.isSingleJump(5,4,3,6));
        assertTrue(game.isSingleJump(5,4,7,6));

        // White turn
        game.setTurnCounter(1);
        assertFalse(game.isSingleJump(5,6,7,4));
        assertTrue(game.isSingleJump(6,1,4,3));
    }


    @Test
    void testMoveToDiagonallyAdjacent() {
        // Red turn
        game.setTurnCounter(1);
        assertTrue(game.isSingleJump(6,1,4,3));
        assertFalse(game.isSingleJump(6,1,3,4));
    }

    @Test
    void testOnlyMoveCurrentPlayerCheckers() {
        // Red turn
        game.setTurnCounter(0);

        // Move up with white checker
        assertFalse(game.isSingleJump(2,3,4,1));

        // White turn
        game.setTurnCounter(1);
        // Move up with white checker
        assertFalse(game.isSingleJump(3,2,1,4));
    }


}