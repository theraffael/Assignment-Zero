package model;

import java.util.ArrayList;

public class Checker {

    private PlayerColor playerColor;

    private boolean king = false;
    public void crown()
    {
        king = true;
    }
    public boolean isKing() {
        return king;
    }

    public Checker(PlayerColor c){
        this.playerColor = c;
    }

    // Copy checker
    public Checker(Checker oldChecker){
        this.playerColor= oldChecker.playerColor;
        this.king = oldChecker.king;
    }


    public PlayerColor playerColor() {
        if (playerColor == PlayerColor.WHITE) {
            return PlayerColor.WHITE;
        }
        else {
            return PlayerColor.RED;
        }
    }

    public String toString(){
        return getSymbol();
    }

    private String getSymbol() {
        String color;
        if(playerColor == PlayerColor.RED){
            color = "R";
        }
        else{
            color = "W";
        }
        if (king){
            return color + "_K";
        }
        else{
            return color + "_P";
        }
    }
}