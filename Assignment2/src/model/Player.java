package model;

import java.util.ArrayList;

public class Player {
    private String color;

    public Player(String color) {
        this.color = color;
    }

    public ArrayList findPlayerCheckers(Board board) {
        ArrayList checkers = new ArrayList();
        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                if (board.fieldContainsCheckerColor(x, y, this.color)) {
                    checkers.add(new Position(x,y));
                }
            }
        }
        return checkers;
    }

    public String getColor() {
        return color;
    }

    public String getColorWord() {
        if (color == "W") {
            return "White";
        }
        else {
            return "Red";
        }
    }

}
