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
        this.s = "unk";
        this.toX = toX;
        this.toY = toY;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;
    private final String s;

    public boolean isMoveJump(){
        if(s == "jump"){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "AI Player Move{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                ", move type='" + s + '\'' +
                '}';
    }
}

