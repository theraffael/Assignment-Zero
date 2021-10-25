package model;

import java.util.ArrayList;

public class Checker {

    private String color;

    public ArrayList<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(int x, int y, int toX, int toY, String s) {
        Move coordinate = new Move(x, y ,toX, toY, s);
        this.possibleMoves.add(coordinate);
    }
    public void clearPossibleMoves(){
        this.possibleMoves.clear();
    }
    public void isNotCaptured(){
        captured = false;
    }
    private ArrayList<Move> possibleMoves = new ArrayList<Move>();
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

    // Copy checker
    public Checker(Checker oldChecker){
        this.color= oldChecker.color;
        this.king = oldChecker.king;
        this.captured = oldChecker.captured;
    }

    public String getColor() {
        return color;
    }

    public String toString(){
        return getSymbol();
    }

    private String getSymbol() {
        if (king){
            return color + "_K";
        }
        else{
            return color + "_P";
        }
    }
}
