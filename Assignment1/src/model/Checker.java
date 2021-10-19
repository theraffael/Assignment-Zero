package model;

import java.util.ArrayList;

public class Checker {

    private String color;

    public ArrayList<Coordinate> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(int x, int y, String s) {
        Coordinate coordinate = new Coordinate(x, y, s);
        this.possibleMoves.add(coordinate);
    }

    private ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
    private boolean king = false;
    public void crown()
    {
        king = true;
    }
    public boolean isKing() {
        return king;
    }

    private boolean captured = false;
    public void capture() {captured = true;}
    public boolean isCaptured() {return captured;}

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private int xPos;
    private int yPos;

    public Checker(String c){
        color = c;
    }

    public String getColor() {
        return color;
    }

    public String toString(){
        return getSymbol();
    }

    public String getSymbol() {
        if (king){
            return color + "_K";
        }
        else{
            return color + "_P";
        }
    }
}
