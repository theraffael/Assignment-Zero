package model;

import java.util.ArrayList;

public class Player {
    private String color;
    private ArrayList checkers = new ArrayList();
    private boolean canMove;

    public Player(String color) {
        this.color = color;
        //fills the players list with checker objects.
        for(int i = 0; i < 12; i++)
        {
            checkers.add(new Checker(color));
        }
    }

    public ArrayList getCheckers() {
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
    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
