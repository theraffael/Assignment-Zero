package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;

import java.util.ArrayList;

public interface PlayerStrategy {
    // Todo: which parameters do we actually need?
    ArrayList<Move> getMove(ArrayList<ArrayList<Move>> possibleMoves, ArrayList opposingPlayerCheckers, UI ui);
}
