package logic;

import model.Move;
import model.PlayerColor;
import model.UI;

import java.util.ArrayList;

public class PlayerContext {
    public PlayerStrategy getStrategy() {
        return strategy;
    }

    private PlayerStrategy strategy;
    private PlayerColor playerColor;

    public PlayerContext(PlayerStrategy strategy, PlayerColor color){
        this.strategy = strategy;
        this.playerColor= color;
    }

    public ArrayList<Move> getMove(ArrayList<ArrayList<Move>> possibleMoves, ArrayList opposingPlayerCheckers, UI ui){
        return strategy.getMove(possibleMoves,opposingPlayerCheckers, ui);
    }

    public String playerColorToString() {
        if (this.playerColor == PlayerColor.WHITE) {
            return "White";
        }
        else {
            return "Red";
        }
    }
}
