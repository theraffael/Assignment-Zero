package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(){
        this.cards = initializeDeck();
    }

    public Card draw(){
        // return card and remove it from deck
        // todo: check if any cards are left in deck first
        return this.cards.remove(0);
    }

    private ArrayList<Card> initializeDeck(){
        // Populate array with the 52 cards (13 ranks times 4 suits)
        ArrayList<Card> cards = new ArrayList();
        for (Suit suit: Suit.values()){
            for (Rank rank: Rank.values()){
                cards.add(new Card(rank, suit));
            }
        }

        // shuffle the array
        Collections.shuffle(cards);
        return cards;
    }
}