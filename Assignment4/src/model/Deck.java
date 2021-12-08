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

    public String toString(){
        String cardsString = "";
        int i = 0; //only to see if it works
        for (Card aCard : this.cards){
            cardsString += "\nCard number " + i + " = " + aCard.getString();
            i++;
        }
        return cardsString;
    }

    //implement ascii-style cards
    public String toStringFancy(){
        return "notTheRealStuff";
    }
    //remove card from deck
    public void removeCard(int i){
        this.cards.remove(i);
    }

    //get a specific card
    public Card getCard(int i){
        return this.cards.get(i);
    }

    //add a specific card to the deck
    public void addCard(Card newCard){
        this.cards.add(newCard);
    }

    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int cardsTotalValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.cards){
            switch(aCard.getRank()){
                case TWO -> {
                    totalValue += 2;
                    break;
                }
                case THREE -> {
                    totalValue += 3;
                    break;
                }
                case FOUR -> {
                    totalValue += 4;
                    break;
                }
                case FIVE -> {
                    totalValue += 5;
                    break;
                }
                case SIX -> {
                    totalValue += 6;
                    break;
                }
                case SEVEN -> {
                    totalValue+= 7;
                    break;
                }
                case EIGHT -> {
                    totalValue += 8;
                    break;
                }
                case NINE -> {
                    totalValue += 9;
                    break;
                }
                case TEN, KING, JACK, QUEEN -> {
                    totalValue += 10;
                    break;
                }
                case ACE -> {
                    totalValue += 1;
                    break;
                }
            }
        }

        for (int i=0; i < aces; i++){
            if(totalValue>10){
                totalValue += 1;
            }
            else{
                totalValue += 11;
            }
        }
        return totalValue;
    }


    public int deckSize(){
        return this.cards.size();
    }


    public void moveAllCardsBackToDeck(Deck targetDeck){
        int thisDeckSize = this.cards.size();

        for (int i = 0; i < thisDeckSize; i++){
            targetDeck.addCard(this.getCard(i));
        }

        for (int i =0; i < thisDeckSize; i++){
            this.removeCard(i);
        }
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