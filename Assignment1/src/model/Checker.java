package model;

public class Checker {
    private String color;
    private boolean king = false;

    public boolean isKing() {
        return king;
    }

    public Checker(String c){
        color = c;
    }

    public void crown()
    {
        king = true;
    }
    public String toString(){
        return getColor();
    }

    public String getColor() {
        return color;
    }

}
