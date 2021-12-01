package model;

import java.util.List;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String getString(){
        return rank.name() + " of " + suit.name();
    }

    public List<Integer> getValue(){
        // todo: implement
        return List.of();
    }

}
