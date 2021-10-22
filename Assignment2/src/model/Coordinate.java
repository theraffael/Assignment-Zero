package model;

public class Coordinate {
    public Coordinate(int fromX, int fromY, int toX, int toY, String s) {
        this.x = fromX;
        this.y = fromY;
        this.s = s;
        this.toX = toX;
        this.toY = toY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;



    private int toX;
    private int toY;

    public String getS() {
        return s;
    }

    private String s;
}

