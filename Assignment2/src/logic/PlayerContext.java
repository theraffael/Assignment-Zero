package logic;

import model.Board;
import model.Move;
import model.PlayerColor;

public class PlayerContext {
    private PlayerStrategy strategy;

    public PlayerContext(PlayerStrategy strategy){
        this.strategy = strategy;
    }

    public Move getMove(Board board, PlayerColor color){
        return strategy.getMove(board, color);
    }
}
