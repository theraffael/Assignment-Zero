package logic;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItalianGameTest {
    private static ItalianGame game;
    private static Board board;

    @BeforeEach
    public void setUp(){
        board = new Board(false);
        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);

/*      Setup board for testing purposes:
             0      1      2      3      4      5      6      7
             a      b      c      d      e      f      g      h
          +_______________________________________________________+

    0   1 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

    1   2 |  [   ]  [   ]  [W_K]  [   ]  [   ]  [   ]  [W_K]  [   ]

    2   3 |  [   ]  [R_K]  [   ]  [R_P]  [   ]  [R_P]  [   ]  [   ]

    3   4 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [   ]  [   ]

    4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [R_P]  [   ]  [   ]

    5   6 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [W_P]  [   ]

    6   7 |  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]  [   ]

    7   8 |  [   ]  [   ]  [R_P]  [   ]  [   ]  [   ]  [   ]  [   ]
*/
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(king, 6,1);
        Checker king2 = new Checker(PlayerColor.WHITE);
        king2.crown();
        board.addPiece(king2, 2,1);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 6,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 5,6);

        Checker king3 = new Checker(PlayerColor.RED);
        king3.crown();
        board.addPiece(king3, 1,2);
        board.addPiece(new Checker(PlayerColor.RED), 3,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,4);
        board.addPiece(new Checker(PlayerColor.RED), 2,7);

        game = new ItalianGame(redPlayer, whitePlayer, board);
    }

    @Test
    void testOnlyKingsCaptureKings() {
        // Red turn
        //game.setTurnCounter(0);
        // Only move down with red checker
        Move m1 = new Move(3,2,1,0);
        Move m2 = new Move(1,2,3,0);
        Move m3 = new Move(2,1,0,3);
        Move m4 = new Move(2,1,4,3);

        assertFalse(game.isSingleJump(m1, board),"Jump from (5,4 to 7,6)  with red checker");
        assertTrue(game.isSingleJump(m2, board),"Jump from (5,2 to 7,8) with red checker");

        game.pass();

        assertTrue(game.isSingleJump(m3, board));
        assertTrue(game.isSingleJump(m4, board));
    }


    @Test
    void testOnlyMoveInAllowedDirection() {
        // Red turn
        //game.setTurnCounter(0);
        // Only move down with red checker
        Move m1 = new Move(5,4,7,6);
        Move m2 = new Move(5,2,7,0);

        assertTrue(game.isSingleJump(m1, board),"Jump from (5,4 to 7,6)  with red checker");
        assertFalse(game.isSingleJump(m2, board),"Jump from (5,2 to 7,8) with red checker");
    }


    @Test
    void testOnlyMoveToEmptySquare() {
        // Red turn
        //game.setTurnCounter(0);
        assertFalse(game.isSingleJump(new Move(7,4,5,6), board));
        assertTrue(game.isSingleJump(new Move(5,4,7,6), board));
    }


    @Test
    void testOnlyJumpIfOpponentIsThere() {
        // Red turn
        //game.setTurnCounter(0);
        assertFalse(game.isSingleJump(new Move(5,4,3,6), board));
        assertTrue(game.isSingleJump(new Move(5,4,7,6), board));

        // White turn
        game.pass();
        assertFalse(game.isSingleJump(new Move(5,6,7,4), board));
        assertTrue(game.isSingleJump(new Move(6,1,4,3), board));
    }


    @Test
    void testMoveToDiagonallyAdjacent() {
        // Red turn
        game.pass();
        assertTrue(game.isSingleJump(new Move(6,1,4,3), board));
        assertFalse(game.isSingleJump(new Move(6,1,3,4), board));
    }

    @Test
    void testOnlyMoveCurrentPlayerCheckers() {
        // Red turn
        //game.setTurnCounter(0);

        // Move up with white checker
        assertFalse(game.isSingleJump(new Move(2,3,4,1), board));

        // White turn
        game.pass();
        // Move up with white checker
        assertFalse(game.isSingleJump(new Move(3,2,1,4), board));
    }


}