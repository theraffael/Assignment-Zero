package model;

public class Checker {
    private String color;

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

    public Checker(String c){
        color = c;
    }

    public String toString(){
        return getColor();
    }

    public String getColor() {
        if (king){
            return color + "_K";
        }
        else{
            return color + "_P";
        }

    }

}
