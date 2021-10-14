package logic;
import model.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
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

    1   2 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]

    2   3 |  [   ]  [   ]  [   ]  [R_P]  [   ]  [R_P]  [   ]  [   ]

    3   4 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [   ]  [   ]

    4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [R_P]  [   ]  [R_P]

    5   6 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [W_P]  [   ]

    6   7 |  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]  [   ]

    7   8 |  [   ]  [   ]  [R_P]  [   ]  [   ]  [   ]  [   ]  [   ]
*/

        board.addPiece(whitePlayer.getCheckers().get(0), 6,1);
        board.addPiece(whitePlayer.getCheckers().get(1), 2,3);
        board.addPiece(whitePlayer.getCheckers().get(2), 2,5);
        board.addPiece(whitePlayer.getCheckers().get(3), 6,5);
        board.addPiece(whitePlayer.getCheckers().get(4), 5,6);

        board.addPiece(redPlayer.getCheckers().get(0), 3,2);
        board.addPiece(redPlayer.getCheckers().get(1), 5,2);
        board.addPiece(redPlayer.getCheckers().get(2), 5,4);
        board.addPiece(redPlayer.getCheckers().get(3), 7,4);
        board.addPiece(redPlayer.getCheckers().get(5), 2,7);

        game = new Game(board, redPlayer, whitePlayer);
    }


    @org.junit.jupiter.api.Test
    void testOnlyMoveInAllowedDirectionRed() {
        // Red turn
        game.setTurnCounter(0);
        // Only move down with red checker
        assertTrue(game.isMove(5,4,4,5),
                "Move down (5,4 to 4,5)  with red checker");
        assertFalse(game.isMove(5,4,4,3),
                "Move up (5,4 to 4,3) with red checker");
    }

    @org.junit.jupiter.api.Test
    void testOnlyMoveInAllowedDirectionWhite() {
        // White turn
        game.setTurnCounter(1);
        // Only move up with white checker
        assertTrue(game.isMove(6,1,5,0),
                "Move up (6,1 to 5,0) with white checker");
        assertFalse(game.isMove(6,1,7,2),
                "Move down (6,1 to 7,2) with white checker");
    }

    @org.junit.jupiter.api.Test
    void testOnlyMoveToEmptySquare() {
        // Red turn
        game.setTurnCounter(0);
        assertFalse(game.isMove(3,2,2,3),"2,3 is not an empty square");
        assertTrue(game.isMove(3,2,4,3), "4,3 is an empty square");
    }

    @org.junit.jupiter.api.Test
    void testMoveToDiagonallyAdjacent() {
        // Red turn
        game.setTurnCounter(0);
        assertTrue(game.isMove(5,2,6,3),"5,2 and 6,3 are diagonally adjacent");
        assertFalse(game.isMove(5,2,5,3), "5,2 and 5,3 are not diagonally adjacent");
        assertFalse(game.isMove(5,2,4,7), "5,2 and 4,7 are not diagonally adjacent");
    }

    @org.junit.jupiter.api.Test
    void testOnlyMoveCurrentPlayerCheckers() {
        // Red turn
        game.setTurnCounter(0);

        // Move down with white checker
        assertFalse(game.isMove(5,6,4,7),
                "Move down (5,6 to 4,7)  with white checker on Red turn");

        // Move up with white checker
        assertFalse(game.isMove(5,6,4,5),
                "Move up (5,6 to 4,5)  with white checker on Red turn");
    }



}