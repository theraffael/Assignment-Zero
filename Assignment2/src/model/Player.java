package model;

import java.util.ArrayList;

public class Player {
    private PlayerColor playerColor;

    public Player(PlayerColor color) {
        this.playerColor= color;
    }

    public ArrayList findPlayerCheckers(Board board) {
        ArrayList checkers = new ArrayList();
        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                if (board.fieldContainsCheckerColor(x, y, this.playerColor)) {
                    checkers.add(new Position(x,y));
                }
            }
        }
        return checkers;
    }

    public boolean isRedPlayersTurn() {
        if(this.playerColor == PlayerColor.RED){
            return true;
        }
        else{
            return false;
        }
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
