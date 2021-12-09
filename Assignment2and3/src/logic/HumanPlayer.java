package logic;

import model.Move;
import model.PlayerColor;
import model.UI;

import java.util.ArrayList;

public class HumanPlayer implements PlayerStrategy {

    public ArrayList<Move> getMove(Game game, UI ui, PlayerColor playerColor){
        return ui.handleInput();
    }
}
