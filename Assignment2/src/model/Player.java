package model;

import java.util.ArrayList;

public class Player {
    private PlayerColor playerColor;

    public Player(PlayerColor color) {
        this.playerColor= color;
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
