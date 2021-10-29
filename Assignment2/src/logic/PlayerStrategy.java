package logic;

import model.Board;
import model.Move;
import model.PlayerColor;

public interface PlayerStrategy {
    // Todo: which parameters do we actually need?
    Move getMove(Board board, PlayerColor color);
}
