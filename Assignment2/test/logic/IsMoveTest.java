package logic;
import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsMoveTest {
    private static Game game;
    private static Board board;

    @BeforeEach
    public void setUp(){
        board = new Board(false);
        Player redPlayer = new Player(PlayerColor.RED);
        Player whitePlayer = new Player(PlayerColor.WHITE);

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
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(new Checker(PlayerColor.WHITE), 6,1);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(king, 2,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 6,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 5,6);

        board.addPiece(new Checker(PlayerColor.RED), 3,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,4);
        board.addPiece(new Checker(PlayerColor.RED), 7,4);
        board.addPiece(new Checker(PlayerColor.RED), 2,7);

        game = new Game(board, redPlayer, whitePlayer);
    }


    @Test
    void testOnlyMoveInAllowedDirectionRed() {
        // Red turn
        game.setTurnCounter(0);
        // Only move down with red checker
        assertTrue(game.isMove(new Move(5,4,4,5), board),
                "Move down (5,4 to 4,5)  with red checker");
        assertFalse(game.isMove(new Move(5,4,4,3), board),
                "Move up (5,4 to 4,3) with red checker");
    }

    @Test
    void testOnlyMoveInAllowedDirectionWhite() {
        // White turn
        game.setTurnCounter(1);
        // Only move up with white checker
        assertTrue(game.isMove(new Move(6,1,5,0), board),
                "Move up (6,1 to 5,0) with white checker");
        assertFalse(game.isMove(new Move(6,1,7,2), board),
                "Move down (6,1 to 7,2) with white checker");
    }

    @Test
    void testKingsCanMoveUpAndDown() {
        // White turn
        game.setTurnCounter(1);
        // Move up and down with white king
        assertTrue(game.isMove(new Move(2,5,3,4), board),
                "Move up (2,5 to 3,4) with white checker");
        assertTrue(game.isMove(new Move(2,5,1,6), board),
                "Move down (2,5 to 1,6) with white checker");
    }

    @Test
    void testOnlyMoveToEmptySquare() {
        // Red turn
        game.setTurnCounter(0);
        assertFalse(game.isMove(new Move(3,2,2,3), board),"2,3 is not an empty square");
        assertTrue(game.isMove(new Move(3,2,4,3), board), "4,3 is an empty square");
    }

    @Test
    void testMoveToDiagonallyAdjacent() {
        // Red turn
        game.setTurnCounter(0);
        assertTrue(game.isMove(new Move(5,2,6,3), board),"5,2 and 6,3 are diagonally adjacent");
        assertFalse(game.isMove(new Move(5,2,5,3),board), "5,2 and 5,3 are not diagonally adjacent");
        assertFalse(game.isMove(new Move(5,2,4,7), board),"5,2 and 4,7 are not diagonally adjacent");
    }

    @Test
    void testOnlyMoveCurrentPlayerCheckers() {
        // Red turn
        game.setTurnCounter(0);

        // Move down with white checker
        assertFalse(game.isMove(new Move(5,6,4,7), board),
                "Move down (5,6 to 4,7)  with white checker on Red turn");

        // Move up with white checker
        assertFalse(game.isMove(new Move(5,6,4,5), board),
                "Move up (5,6 to 4,5)  with white checker on Red turn");
    }


}