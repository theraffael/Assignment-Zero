package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;
import java.util.ArrayList;

public class MinMaxPlayer implements PlayerStrategy {
    private PlayerColor playerColor;

    public ArrayList<Move> getMove(Game game, UI ui, PlayerColor playerColor){
        this.playerColor = playerColor;
        ArrayList<ArrayList<Move>> possibleMoves = game.calcPossibleMoves();
        return new ArrayList();
    }

    private Integer getValue(Game game){
        if (game.isFinished()){
            return 100;
        }
        else{
            return 2;
        }
    }

    private Integer minimax(Game game, Integer depth){
        if (depth == 0 || game.isFinished()){
            this.getValue(game);
        }

        if (game.getActivePlayer() == this.playerColor){
            int max = 0;
            for (ArrayList<Move> move : game.calcPossibleMoves()){
                Game gameCopy = new Game(game);
                game.newMove(move);
                int val = minimax(gameCopy, depth -1);
                max = Math.max(val, max);
            }
            return max;
        }
        else{
            int min = 100;
            for (ArrayList<Move> move : game.calcPossibleMoves()){
                Game gameCopy = new Game(game);
                int val = minimax(gameCopy, depth -1);
                min = Math.min(val, min);
            }
            return min;
        }
    }
/*
    function minimax(node, depth, maximizingPlayer) is
if depth ==0 or node is a terminal node then
return static evaluation of node

if MaximizingPlayer then      // for Maximizer Player
    maxEva= -infinity
 for each child of node do
    eva= minimax(child, depth-1, false)
    maxEva= max(maxEva,eva)        //gives Maximum of the values
return maxEva

else                         // for Minimizer player
    minEva= +infinity
 for each child of node do
    eva= minimax(child, depth-1, true)
    minEva= min(minEva, eva)         //gives minimum of the values
 return minEva
 */
}
