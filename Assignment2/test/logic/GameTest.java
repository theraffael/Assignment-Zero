package logic;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class GameTest {

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
        Game game = new Game(board, redPlayer, whitePlayer, new UI(false));

        Move m1 = new Move(0,5, 2,7, "jump");

        ArrayList oneJump = new ArrayList();
        oneJump.add(m1);

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
        Game game = new Game(board, redPlayer, whitePlayer, new UI(false));

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
        Game game = new Game(board, redPlayer, whitePlayer, new UI(false));

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
    void dontMoveWhenJumpPossible() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(new Checker(PlayerColor.RED), 3,2);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new Game(board, redPlayer, whitePlayer, new UI(false));

        Move m1 = new Move(3,2, 4,3, "move");
        ArrayList move = new ArrayList();
        move.add(m1);

        assertFalse(game.newMove(move));
    }

    @Test
    void isFinishedNoCheckersLeft() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new Game(board, redPlayer, whitePlayer, new UI(false));

        assertTrue(game.isFinished());
    }


    @Test
    void isFinishedNoMovesLeft() {
        Board board = new Board(false);
        board.addPiece(new Checker(PlayerColor.WHITE), 5,2);
        board.addPiece(new Checker(PlayerColor.WHITE), 6,1);
        board.addPiece(new Checker(PlayerColor.RED), 7,0);

        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);
        Game game = new Game(board, redPlayer, whitePlayer, new UI(false));

        assertTrue(game.isFinished());
    }

}
