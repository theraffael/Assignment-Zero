import com.sun.source.tree.AssertTree;
import logic.*;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;


public class CheckerGameTest {

    @Test
    void MinMaxVsRandom(){ //todo: suppress output of game.runGame()


        for (int k = 0; k<10 ; k++){
            Board board = new Board(true);
            PlayerContext redPlayer = new PlayerContext(new RandomPlayer(), PlayerColor.RED);
            PlayerContext whitePlayer = new PlayerContext(new MinMaxPlayer(), PlayerColor.WHITE);

            Game game = new Game(redPlayer, whitePlayer, board);

            //System.out.println(board.getBoardString());
            //System.out.println(game.isFinished());
            game.runGame();
            //System.out.println(board.getBoardString());

            System.out.println("Run number " + k + " was lost by: " + game.getActivePlayer().toString());
            assertTrue(game.isFinished());
            assertEquals(PlayerColor.RED, game.getActivePlayer());
        }

    }
}
