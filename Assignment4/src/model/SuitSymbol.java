package model;

public enum SuitSymbol {
    SPADES("♠"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    CLUBS("♣");

    private String value;
    private SuitSymbol(String value){
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
