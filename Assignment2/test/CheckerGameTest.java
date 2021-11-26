import com.sun.source.tree.AssertTree;
import logic.*;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;


public class CheckerGameTest {

    @Test
    void MinMaxVsRandom(){
        int runs = 10;
        int wonByMinMay = 0;
        System.out.println("\n----------\nBEHOLD! In the following test you shall bear witness to not one but "+runs+" matches between the great MinMax-Player and the not-so-great Random Player!\n----------\n");


        for (int i = 0; i< runs ; i++){
            Board board = new Board(true);
            PlayerContext redPlayer = new PlayerContext(new RandomPlayer(), PlayerColor.RED);
            PlayerContext whitePlayer = new PlayerContext(new MinMaxPlayer(), PlayerColor.WHITE);

            Game game = new Game(redPlayer, whitePlayer, board);

            //System.out.println(board.getBoardString());
            //System.out.println(game.isFinished());
            game.runGame();
            //System.out.println(board.getBoardString());

            System.out.println("Run number " + i + " was lost by: " + game.getActivePlayer().toString());
            if (game.isFinished() && (game.getActivePlayer() == PlayerColor.RED)){
                wonByMinMay++;
            }
            assertTrue(game.isFinished());
            assertEquals(PlayerColor.RED, game.getActivePlayer());
        }
        assertTrue(wonByMinMay> (runs-1)); //sometimes the random player wins, which is expected although unlikely

        System.out.println("\n--------------\n" +
                "The MinMax Player won "+wonByMinMay+" out of "+ runs +" times (9 times needed to pass).\n--------------\n");

    }
}
