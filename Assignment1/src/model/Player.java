package model;

import java.util.ArrayList;

public class Player {
    private String color;
    private ArrayList checkers = new ArrayList();
    private ArrayList checkersPositions = new ArrayList();
    private boolean canMove;
    private int checkerCount = 0;

    public Player(String color) {
        this.color = color;
        //fills the players list with checker objects.
        for(int i = 0; i < 12; i++)
        {
            checkers.add(new Checker(color));
            checkerCount++;
        }
    }

    public ArrayList getCheckers() {
        return checkers;
    }

    public void setCheckersPositions(int x, int y){
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(x);
        coordinates.add(y);
        checkersPositions.add(coordinates);
    }
    public ArrayList getCheckersPositions() {
        return checkersPositions;
    }
    public ArrayList<Integer> getCheckerPosition(int i){
        ArrayList allPositions = getCheckersPositions();
        ArrayList<Integer> wantedPosition = (ArrayList<Integer>) allPositions.get(i);
        return wantedPosition;
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
