package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

public class RandomPlayer implements PlayerStrategy {

    public ArrayList<Move> getMove(ArrayList<Move> possibleMoves, ArrayList opposingPlayerCheckers, UI ui){
        int possibleMovesSize = possibleMoves.size();
        int min_val = 0;
        int max_val = possibleMovesSize - 1;
        SecureRandom rand = new SecureRandom();
        rand.setSeed(new Date().getTime());
        int randomNum = rand.nextInt((max_val - min_val) + 1) + min_val;
        ArrayList possibleMove = new ArrayList();
        possibleMove.add(possibleMoves.get(randomNum));
        ui.outputMoveToConsole(possibleMove.get(0).toString());
        return possibleMove;
    }
}
