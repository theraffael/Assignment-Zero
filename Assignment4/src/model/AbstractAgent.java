package model;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractAgent {
    protected ArrayList<Card> handCards = new ArrayList<Card>();
    protected ArrayList<Integer> handValue = new ArrayList<Integer>();

    abstract Call hitOrStay();

    public void addCardToHand(Card card){
        this.handCards.add(card);
    }

    protected int largestHandValue(){
        if (handValue.size() == 0){
            return 0;
        }
        return Collections.max(handValue);
    }

    public ArrayList<Card> getHandCards(){
        return handCards;
    }

    public Integer totalHandValue(){
        return handValue.stream().mapToInt(Integer::intValue).sum();
    }
}
