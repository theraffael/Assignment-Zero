package logic;

import model.Board;
import model.Checker;
import model.Move;
import model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsJumpFinished {

    @Test
    void isJumpFinished() {
        Board board = new Board();
        board.addPiece(new Checker("W"), 2,3);
        board.addPiece(new Checker("W"), 2,5);
        board.addPiece(new Checker("R"), 3,2);

        Player redPlayer = new Player("R");
        Player whitePlayer = new Player("W");
        Game game = new Game(board, redPlayer, whitePlayer);

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
        Board board = new Board();
        Checker king = new Checker("W");
        king.crown();
        board.addPiece(king, 2,3);
        board.addPiece(new Checker("R"), 3,2);
        board.addPiece(new Checker("R"), 5,2);
        board.addPiece(new Checker("R"), 5,4);

        Player redPlayer = new Player("R");
        Player whitePlayer = new Player("W");
        Game game = new Game(board, redPlayer, whitePlayer);
        game.setTurnCounter(1);

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


}
