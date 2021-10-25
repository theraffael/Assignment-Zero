package model;

public class Move {
    public Move(int fromX, int fromY, int toX, int toY, String s) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.s = s;
        this.toX = toX;
        this.toY = toY;
    }

    public Move(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    public int fromX;
    public int fromY;
    public int toX;
    public int toY;

    public String getS() {
        return s;
    }

    private String s;
}

