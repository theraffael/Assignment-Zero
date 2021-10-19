package model;

public class Coordinate {
    public Coordinate(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }

    private int x;
    private int y;

    public String getS() {
        return s;
    }

    private String s;
}
