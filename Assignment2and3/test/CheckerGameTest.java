import com.sun.source.tree.AssertTree;
import logic.*;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;


public class CheckerGameTest {
    private void opening(int runs, String mode){
        System.out.println("\n----------\nBEHOLD! In the following test you shall bear witness to not one but "+
                runs + " matches between the great MinMax-Player and the not-so-great Random Player!\n" +
                "Mode: "+ mode +"\n----------\n");

    }
    private void intermediateResult(int i, Game game){
        System.out.println("\nRun number " + i + " was lost by: " + game.getActivePlayer().toString()+"\n");

    }
    private void endResult(int wonByMinMay, int runs, int runsToPass){
        System.out.println("\n--------------\n" +
                "The MinMax Player won " + wonByMinMay + " out of "+ runs +" times " +
                "("+ runsToPass + " times needed to pass)." +
                "\n--------------\n");

    }

    @Test
    void MinMaxVsRandom(){
        int runs = 10;
        int runsToPass = runs -1;
        int wonByMinMay = 0;
        String mode = "Standard";
        opening(runs, mode);

        for (int i = 0; i< runs ; i++){
            Board board = new Board(true);
            PlayerContext redPlayer = new PlayerContext(new RandomPlayer(), PlayerColor.RED);
            PlayerContext whitePlayer = new PlayerContext(new MinMaxPlayer(), PlayerColor.WHITE);

            Game game = new Game(redPlayer, whitePlayer, board);

            game.runGame();

            intermediateResult(i, game);

            if (game.isFinished() && (game.getActivePlayer() == PlayerColor.RED)){
                wonByMinMay++;
            }
            assertTrue(game.isFinished());
            //assertEquals(PlayerColor.RED, game.getActivePlayer());
        }
        assertTrue(wonByMinMay>= (runs-1)); //sometimes the random player wins, which is expected although unlikely

        endResult(wonByMinMay,runs,runsToPass);
    }
    @Test
    void MinMaxVsRandomItalian(){
        int runs = 5;
        int runsToPass = runs-1;
        int wonByMinMay = 0;
        String mode = "Italian";

        opening(runs, mode);


        for (int i = 0; i< runs ; i++){
            Board board = new Board(true);
            PlayerContext redPlayer = new PlayerContext(new RandomPlayer(), PlayerColor.RED);
            PlayerContext whitePlayer = new PlayerContext(new MinMaxPlayer(), PlayerColor.WHITE);

            Game game = new ItalianGame(redPlayer, whitePlayer, board);

            game.runGame();

            intermediateResult(i, game);

            if (game.isFinished() && (game.getActivePlayer() == PlayerColor.RED)){
                wonByMinMay++;
            }
            assertTrue(game.isFinished());
            //assertEquals(PlayerColor.RED, game.getActivePlayer());
        }
        assertTrue(wonByMinMay>= (runs-1)); //sometimes the random player wins, which is expected although unlikely
        endResult(wonByMinMay,runs,runsToPass);
    }

    @Test
    void MinMaxVsRandomSpanish(){
        int runs = 5;
        int runsToPass = runs-1;
        int wonByMinMax = 0;
        String mode = "Spanish";

        opening(runs, mode);


        for (int i = 0; i< runs ; i++){
            Board board = new Board(true);
            PlayerContext redPlayer = new PlayerContext(new RandomPlayer(), PlayerColor.RED);
            PlayerContext whitePlayer = new PlayerContext(new MinMaxPlayer(), PlayerColor.WHITE);

            Game game = new SpanishGame(redPlayer, whitePlayer, board);

            game.runGame();

            intermediateResult(i, game);

            if (game.isFinished() && (game.getActivePlayer() == PlayerColor.RED)){
                wonByMinMax++;
            }
            assertTrue(game.isFinished());
            //assertEquals(PlayerColor.RED, game.getActivePlayer());
        }
        assertTrue(wonByMinMax>= (runs-1)); //sometimes the random player wins, which is expected although unlikely
        endResult(wonByMinMax,runs,runsToPass);
    }

    @Test
    void MinMaxVsRandomTurkish(){
        int runs = 5;
        int runsToPass = runs-1;
        int wonByMinMay = 0;
        String mode = "Turkish";
        opening(runs, mode);


        for (int i = 0; i< runs ; i++){
            Board board = new Board(true);
            PlayerContext redPlayer = new PlayerContext(new RandomPlayer(), PlayerColor.RED);
            PlayerContext whitePlayer = new PlayerContext(new MinMaxPlayer(), PlayerColor.WHITE);

            Game game = new TurkishGame(redPlayer, whitePlayer, board);

            game.runGame();

            intermediateResult(i, game);

            if (game.isFinished() && (game.getActivePlayer() == PlayerColor.RED)){
                wonByMinMay++;
            }
            assertTrue(game.isFinished());
            //assertEquals(PlayerColor.RED, game.getActivePlayer());
        }
        assertTrue(wonByMinMay>= (runs-1)); //sometimes the random player wins, which is expected although unlikely
                endResult(wonByMinMay,runs,runsToPass);
    }

}
