package logic;

import model.Board;
import model.Checker;
import model.Move;
import model.PlayerColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurkishGameTest {

    @Test
    void testOnlyMoveInAllowedDirectionRed() {
        Board board = new Board(false);
        board.placeCheckersTurkishSetup();

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);
        assertTrue(game.isMove(new Move(5,2,5,3), board));
        assertFalse(game.isMove(new Move(5,1,5,0), board));

    }


    @Test
    void testOnlyMoveInAllowedDirectionWhite() {
        Board board = new Board(false);
        board.placeCheckersTurkishSetup();

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        game.pass();
        assertTrue(game.isMove(new Move(5,5,5,4), board));
        assertFalse(game.isMove(new Move(5,6,5,7), board));

    }

    @Test
    void testOnlyMoveToEmptySquare() {
        Board board = new Board(false);
        board.placeCheckersTurkishSetup();

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);
        assertFalse(game.isMove(new Move(5,1,5,2), board));
        assertTrue(game.isMove(new Move(7,2,7,3), board));

    }

    @Test
    void testMoveToOrthogonallyAdjacent() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.RED), 4, 4);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertFalse(game.isMove(new Move(4,4,5,5), board));
        assertFalse(game.isMove(new Move(4,4,6,4), board));
        assertFalse(game.isMove(new Move(4,4,4,7), board));
        assertTrue(game.isMove(new Move(4,4,4,5), board));
        assertTrue(game.isMove(new Move(4,4,5,4), board));
        assertTrue(game.isMove(new Move(4,4,3,4), board));

    }

    @Test
    void testOnlyMoveCurrentPlayerCheckers() {
        Board board = new Board(false);
        board.placeCheckersTurkishSetup();

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertFalse(game.isMove(new Move(5,5,5,4), board));
        game.pass();
        assertFalse(game.isMove(new Move(5,2,5,3), board));

    }

    @Test
    void testKingsCanMoveFar() {
        // Red turn
        // Move back along diagonal with king
        Board board = new Board(false);
        Checker king = new Checker(PlayerColor.RED);
        king.crown();
        board.addPiece(king, 4, 4);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertFalse(game.isMove(new Move(4,4,5,5), board));
        assertFalse(game.isMove(new Move(4,4,6,0), board));
        assertTrue(game.isMove(new Move(4,4,4,7), board));
        assertTrue(game.isMove(new Move(4,4,0,4), board));
        assertTrue(game.isMove(new Move(4,4,4,0), board));
        assertTrue(game.isMove(new Move(4,4,7,4), board));
    }


    @Test
    void testOnlyJumpInAllowedDirection() {
        // Red turn
        // Only move down with red checker
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.RED), 4, 4);
        board.addPiece(new Checker(PlayerColor.WHITE), 4, 5);
        board.addPiece(new Checker(PlayerColor.WHITE), 4, 3);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertTrue(game.isSingleJump(new Move(4,4,4,6), board));
        assertFalse(game.isSingleJump(new Move(4,4,4,2), board));
    }

    @Test
    void testJumpWithKing() {
        // Red turn
        // Only move down with red checker
        Board board = new Board(false);
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(king, 5,1);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,6);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);
        game.pass();

        assertTrue(game.isSingleJump(new Move(5,1,5,4), board));
        assertFalse(game.isSingleJump(new Move(5,1,5,7), board));
    }


    @Test
    void testOnlyJumpToEmptySquare() {

        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 4, 3);
        board.addPiece(new Checker(PlayerColor.RED), 4, 4);
        board.addPiece(new Checker(PlayerColor.WHITE), 4, 5);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertTrue(game.isSingleJump(new Move(4,4,4,6), board));
        game.pass();
        assertFalse(game.isSingleJump(new Move(4,5,4,3), board));
    }


    @Test
    void testOnlyJumpIfOpponentIsThere() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 4, 3);
        board.addPiece(new Checker(PlayerColor.RED), 4, 4);
        board.addPiece(new Checker(PlayerColor.WHITE), 5, 4);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertTrue(game.isSingleJump(new Move(4,4,6,4), board));
        assertFalse(game.isSingleJump(new Move(4,4,2,4), board));
        game.pass();
        assertTrue(game.isSingleJump(new Move(5,4,3,4), board));
        assertFalse(game.isSingleJump(new Move(5,4,7,4), board));
    }


    @Test
    void testOnlyJumpCurrentPlayerCheckers() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.RED), 4, 4);
        board.addPiece(new Checker(PlayerColor.WHITE), 5, 4);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        assertFalse(game.isSingleJump(new Move(5,4,3,4), board));
        assertTrue(game.isSingleJump(new Move(4,4,6,4), board));
        game.pass();
        assertTrue(game.isSingleJump(new Move(5,4,3,4), board));
        assertFalse(game.isSingleJump(new Move(4,4,6,4), board));

    }


    @Test
    void isJumpFinished() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 3,3);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,4);
        board.addPiece(new Checker(PlayerColor.RED), 3,2);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        Move m1 = new Move(3,2, 3,4, "jump");
        Move m2 = new Move(3,4, 1,4, "jump");

        ArrayList oneJump = new ArrayList();
        ArrayList twoJumps = new ArrayList();
        oneJump.add(m1);
        twoJumps.add(m1);
        twoJumps.add(m2);

        assertFalse(game.newMove(oneJump));
        assertTrue(game.newMove(twoJumps));

    }

    @Test
    void isSpanishMultiJumpFinished() {
        Board board = new Board(false);
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(king, 0,1);
        board.addPiece(new Checker(PlayerColor.RED), 2,1);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,6);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);
        game.pass();

        Move m1 = new Move(0,1, 5,1, "jump");
        Move m2 = new Move(5,1, 5,4, "jump");
        Move m3 = new Move(5,4, 5,7, "jump");

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
        board.addPiece(new Checker(PlayerColor.RED), 3,3);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);
        Move m1 = new Move(3,3, 4,3, "move");
        ArrayList move = new ArrayList();
        move.add(m1);

        assertFalse(game.newMove(move));
    }

    @Test
    void isJumpFinishedWhenCrowned() {
        /*
        When a single piece reaches the row of the board furthest from the player,
        i.e the king-row, by reason of a simple move, or as the completion of a jump,
        it becomes a king. This ends the player’s turn.
         */
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 3,7);
        board.addPiece(new Checker(PlayerColor.WHITE), 1,6);
        board.addPiece(new Checker(PlayerColor.RED), 1,5);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new TurkishGame(redPlayer, whitePlayer, board);

        Move m1 = new Move(1,5, 1,7, "jump");
        Move m2 = new Move(1,7, 4,7, "jump");

        ArrayList oneJump = new ArrayList();
        ArrayList twoJumps = new ArrayList();
        oneJump.add(m1);
        twoJumps.add(m1);
        twoJumps.add(m2);

        assertFalse(game.newMove(twoJumps));
        assertTrue(game.newMove(oneJump));

    }

}
