package logic;

import model.Move;
import model.PlayerColor;
import model.UI;
import java.util.ArrayList;
import java.util.Collections;

public class MinMaxPlayer implements PlayerStrategy {
    private PlayerColor playerColor;
    private ArrayList<Move> bestMove;
    private Integer bestValue;

    public ArrayList<Move> getMove(Game game, UI ui, PlayerColor playerColor){
        this.playerColor = playerColor;
        this.bestValue = -1;
        this.bestMove = null;
        ArrayList<ArrayList<Move>> possibleMoves = game.calcPossibleMoves();
        // Shuffle to avoid predictability when minmax returns same result for multiple checkers
        Collections.shuffle(possibleMoves);

        for (ArrayList<Move> move : possibleMoves){
            Game gameCopy = new Game(game);
            gameCopy.newMove(move);
            int x = minimax(gameCopy, 3);
            if (x > this.bestValue){
                this.bestValue = x;
                this.bestMove = move;
            }
        }
        for (Object m : this.bestMove){
            ui.outputMoveToConsole(m.toString());
        }
        return this.bestMove;
    }

    private Integer getValue(Game game){
        if (game.isFinished()){
            if (game.getActivePlayer() == this.playerColor){
                return 0;
            }
            else {
                return 20;
            }
        }
        else{
            return game.findPlayerCheckers(this.playerColor).size();
        }
    }

    private Integer minimax(Game game, Integer depth){
        ArrayList<ArrayList<Move>> possibleMoves = game.calcPossibleMoves();
        if (depth == 0 || possibleMoves.isEmpty() || game.isFinished()){
            return this.getValue(game);
        }

        if (game.getActivePlayer() == this.playerColor){
            int max = -1;
            for (ArrayList<Move> move : possibleMoves){
                Game gameCopy = new Game(game);
                gameCopy.newMove(move);
                int val = minimax(gameCopy, depth -1);
                max = Math.max(val, max);
            }
            return max;
        }
        else{
            int min = 100;
            for (ArrayList<Move> move : possibleMoves){
                Game gameCopy = new Game(game);
                gameCopy.newMove(move);
                int val = minimax(gameCopy, depth -1);
                min = Math.min(val, min);
            }
            return min;
        }
    }

}
