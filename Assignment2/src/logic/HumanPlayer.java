package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class HumanPlayer implements PlayerStrategy {

    public ArrayList<Move> getMove(Board board, UI ui){
        return ui.handleInput();
    }
}
