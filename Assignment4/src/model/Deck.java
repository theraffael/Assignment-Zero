package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Iterator {
    /*
    Lightweight implementation of Iterator
    When using the shuffleDeck() method, iterator will be reset
     */

    private ArrayList<Card> cards;
    private int currentPosition;

    public Deck(){
        initializeDeck(1);
    }

    public Deck(Integer numberOfDecks){
        initializeDeck(numberOfDecks);
    }

    private void initializeDeck(Integer numberOfDecks) {
        // Populate array with numberOfDecks x 52 cards (13 ranks times 4 suits per deck)
        this.cards = new ArrayList();
        for (int i = 0; i < numberOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    this.cards.add(new Card(rank, suit));
                }
            }
        }

        // shuffle and set iterator
        shuffleDeck();
    }

    public void shuffleDeck(){
        // shuffle the array and reset the iterator
        this.currentPosition = 0;
        Collections.shuffle(this.cards);
    }

    public int percentageOfCardsLeft(){
        // show progress through the deck, so dealer can decide if it's time to shuffle
        return (this.cards.size() - this.currentPosition) * 100 / cards.size();
    }

    @Override
    public boolean hasNext() {
        return this.currentPosition < this.cards.size();
    }

    @Override
    public Card next() {
        if (this.hasNext()){
            return new Card(cards.get(this.currentPosition++));
        }
        return null;
    }
}