package logic;

import model.Move;
import model.PlayerColor;
import model.UI;

import java.util.ArrayList;

public class PlayerContext {

    private PlayerStrategy strategy;
    private PlayerColor playerColor;

    public PlayerContext(PlayerStrategy strategy, PlayerColor color){
        this.strategy = strategy;
        this.playerColor = color;
    }

    public ArrayList<Move> getMove(Game game, UI ui){
        return strategy.getMove(game, ui, this.playerColor);
    }
}
