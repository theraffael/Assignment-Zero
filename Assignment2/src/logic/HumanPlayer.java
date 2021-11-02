package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;

import java.util.ArrayList;

public class HumanPlayer implements PlayerStrategy {

    public ArrayList<Move> getMove(ArrayList possibleMoves, ArrayList opposingPlayerCheckers, UI ui){
        return ui.handleInput();
    }
}
