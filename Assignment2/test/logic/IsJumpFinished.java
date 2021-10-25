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

}
