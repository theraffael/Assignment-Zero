package model;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractAgent {
    protected ArrayList<Card> handCards;
    protected ArrayList<Integer> handValue;

    abstract Call hitOrStay();

    public void addCardToHand(Card card){
        this.handCards.add(card);
    }

    protected int largestHandValue(){
        return Collections.max(handValue);
    }

    public ArrayList getHandCards(){
        return handCards;
    }

    public Integer totalHandValue(){
        return handValue.stream().mapToInt(Integer::intValue).sum();
    }
}
