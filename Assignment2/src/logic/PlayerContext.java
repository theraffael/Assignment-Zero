package logic;

import model.Board;
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

    public ArrayList<Move> getMove(Board board, UI ui){
        return strategy.getMove(board, ui);
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
