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

    public final int fromX;
    public final int fromY;
    public final int toX;
    public final int toY;
    private String s;

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

