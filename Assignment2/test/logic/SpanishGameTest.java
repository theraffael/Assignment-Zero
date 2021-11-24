package logic;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpanishGameTest {
    private static SpanishGame game;
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
        board.checkAndCrown();

        game = new SpanishGame(redPlayer, whitePlayer, board);
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
    void testOnlyJumpToEmptySquare() {
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
    void testJumpAlongDiagonal() {
        // Red turn
        assertTrue(game.isSingleJump(new Move(1,2,3,4), board));
        assertTrue(game.isSingleJump(new Move(1,2,4,5), board));
        assertFalse(game.isSingleJump(new Move(1,2,5,6), board));
        assertFalse(game.isSingleJump(new Move(1,2,6,7), board));
        game.pass();
        assertTrue(game.isSingleJump(new Move(6,1,3,4), board));
        assertFalse(game.isSingleJump(new Move(6,1,1,6), board));
    }

    @Test
    void testOnlyJumpCurrentPlayerCheckers() {
        // Red turn
        //game.setTurnCounter(0);

        // Move up with white checker
        assertFalse(game.isSingleJump(new Move(2,3,4,1), board));

        // White turn
        game.pass();
        // Move up with white checker
        assertFalse(game.isSingleJump(new Move(3,2,1,4), board));
    }

    @Test
    void testOnlyMoveInAllowedDirectionRed() {
        // Red turn
        // game.setTurnCounter(0);
        // Only move down with red checker
        assertTrue(game.isMove(new Move(5,4,4,5), board),
                "Move down (5,4 to 4,5)  with red checker");
        assertFalse(game.isMove(new Move(5,4,4,3), board),
                "Move up (5,4 to 4,3) with red checker");
    }

    @Test
    void testOnlyMoveInAllowedDirectionWhite() {
        // White turn
        game.pass();
        // Only move up with white checker
        assertTrue(game.isMove(new Move(2,5,1,4), board),
                "Move up (6,1 to 5,0) with white checker");
        assertFalse(game.isMove(new Move(2,5,1,6), board),
                "Move down (6,1 to 7,2) with white checker");
    }

    @Test
    void testKingsCanMoveFar() {
        // Red turn
        // Move back along diagonal with king

        assertTrue(game.isMove(new Move(2,7,1,6), board));
        assertTrue(game.isMove(new Move(2,7,0,5), board));
        assertTrue(game.isMove(new Move(2,7,4,5), board));
        assertFalse(game.isMove(new Move(2,7,6,3), board));
    }

    @Test
    void testOnlyMoveToEmptySquare() {
        // Red turn
        //game.setTurnCounter(0);
        assertFalse(game.isMove(new Move(3,2,2,3), board),"2,3 is not an empty square");
        assertTrue(game.isMove(new Move(3,2,4,3), board), "4,3 is an empty square");
    }

    @Test
    void testMoveToDiagonallyAdjacent() {
        // Red turn
        //game.setTurnCounter(0);
        assertTrue(game.isMove(new Move(5,2,6,3), board),"5,2 and 6,3 are diagonally adjacent");
        assertFalse(game.isMove(new Move(5,2,5,3),board), "5,2 and 5,3 are not diagonally adjacent");
        assertFalse(game.isMove(new Move(5,2,4,7), board),"5,2 and 4,7 are not diagonally adjacent");
    }

    @Test
    void testOnlyMoveCurrentPlayerCheckers() {
        // Red turn
        //game.setTurnCounter(0);

        // Move down with white checker
        assertFalse(game.isMove(new Move(5,6,4,7), board),
                "Move down (5,6 to 4,7)  with white checker on Red turn");

        // Move up with white checker
        assertFalse(game.isMove(new Move(5,6,4,5), board),
                "Move up (5,6 to 4,5)  with white checker on Red turn");
    }

    @Test
    void isJumpFinishedWhenCrowned() {
        /*
        When a single piece reaches the row of the board furthest from the player,
        i.e the king-row, by reason of a simple move, or as the completion of a jump,
        it becomes a king. This ends the player’s turn.
         */
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 1,6);
        board.addPiece(new Checker(PlayerColor.WHITE), 3,6);
        board.addPiece(new Checker(PlayerColor.RED), 0,5);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new SpanishGame(redPlayer, whitePlayer, board);

        Move m1 = new Move(0,5, 2,7, "jump");
        Move m2 = new Move(2,7, 4,5, "jump");

        ArrayList oneJump = new ArrayList();
        ArrayList twoJumps = new ArrayList();
        oneJump.add(m1);
        twoJumps.add(m1);
        twoJumps.add(m2);

        assertFalse(game.newMove(twoJumps));
        assertTrue(game.newMove(oneJump));

    }


    @Test
    void isJumpFinished() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,5);
        board.addPiece(new Checker(PlayerColor.RED), 3,2);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new SpanishGame(redPlayer, whitePlayer, board);

        Move m1 = new Move(3,2, 1,4, "jump");
        Move m2 = new Move(1,4, 3,6, "jump");

        ArrayList oneJump = new ArrayList();
        ArrayList twoJumps = new ArrayList();
        oneJump.add(m1);
        twoJumps.add(m1);
        twoJumps.add(m2);

        assertFalse(game.newMove(oneJump));
        assertTrue(game.newMove(twoJumps));

    }

    @Test
    void isMultiJumpFinished() {
        Board board = new Board(false);
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(king, 2,3);
        board.addPiece(new Checker(PlayerColor.RED), 3,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,4);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new SpanishGame(redPlayer, whitePlayer, board);
        game.pass();

        Move m1 = new Move(2,3, 4,1, "jump");
        Move m2 = new Move(4,1, 6,3, "jump");
        Move m3 = new Move(6,3, 4,5, "jump");

        ArrayList twoJumps = new ArrayList();
        ArrayList threeJumps = new ArrayList();

        twoJumps.add(m1);
        twoJumps.add(m2);

        threeJumps.add(m1);
        threeJumps.add(m2);
        threeJumps.add(m3);

        assertFalse(game.newMove(twoJumps));
        assertTrue(game.newMove(threeJumps));

    }


    @Test
    void isSpanishMultiJumpFinished() {
        Board board = new Board(false);
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(king, 0,3);
        board.addPiece(new Checker(PlayerColor.RED), 2,1);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,6);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new SpanishGame(redPlayer, whitePlayer, board);
        game.pass();

        Move m1 = new Move(0,3, 3,0, "jump");
        Move m2 = new Move(3,0, 7,4, "jump");
        Move m3 = new Move(7,4, 4,7, "jump");

        ArrayList twoJumps = new ArrayList();
        ArrayList threeJumps = new ArrayList();

        twoJumps.add(m1);
        twoJumps.add(m2);

        threeJumps.add(m1);
        threeJumps.add(m2);
        threeJumps.add(m3);

        assertFalse(game.newMove(twoJumps));
        assertTrue(game.newMove(threeJumps));

    }


    @Test
    void dontMoveWhenJumpPossible() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(new Checker(PlayerColor.RED), 3,2);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new SpanishGame(redPlayer, whitePlayer, board);
        Move m1 = new Move(3,2, 4,3, "move");
        ArrayList move = new ArrayList();
        move.add(m1);

        assertFalse(game.newMove(move));
    }

    @Test
    void MoveDebug() {
        Board board = new Board(false);
        Checker king = new Checker(PlayerColor.RED);
        board.addPiece(king, 4,7);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new SpanishGame(redPlayer, whitePlayer, board);
        Move m1 = new Move(4,7, 1,4, "move");
        ArrayList move = new ArrayList();
        move.add(m1);

        assertFalse(game.newMove(move));
    }

}