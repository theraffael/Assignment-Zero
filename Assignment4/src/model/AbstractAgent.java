package model;

import java.util.*;

public abstract class AbstractAgent {
    protected ArrayList<Card> handCards = new ArrayList<Card>();
    protected ArrayList<Integer> handValue = new ArrayList<Integer>();
    private HashMap<Rank, ArrayList<Integer>> cardValues = new HashMap<>();

    abstract Call hitOrStay();

    public void addCardToHand(Card card){
        this.handCards.add(card);
        this.getHandCardsValue();
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

    public void getHandCardsValue(){
        this.initHandCardsValue();
        Card card = handCards.get(handCards.size() - 1);
        ArrayList<Integer> rankValueArray = cardValues.get(card.getRank());
        //check if Rank is ACE
        if (card.getRank() == Rank.ACE){
            if(!handValue.isEmpty()){
                for(int value : handValue){
                    handValue.set(handValue.indexOf(value),value + rankValueArray.get(0));
                    handValue.add(value + rankValueArray.get(1));
                }
            }
            else{
                handValue.add(rankValueArray.get(0));
                handValue.add(rankValueArray.get(1));
            }
        }
        else{
            if(handValue.isEmpty()){
                handValue.add(rankValueArray.get(0));
            }
            else {
                for (int value : handValue) {
                    handValue.set(handValue.indexOf(value), value + rankValueArray.get(0));
                }
            }
        }
    }

    public Integer totalHandValue(){
        return handValue.stream().mapToInt(Integer::intValue).sum();
    }

    private void initHandCardsValue(){
        cardValues.put(Rank.ACE, new ArrayList<>(Arrays.asList(1, 11)));
        cardValues.put(Rank.TWO, new ArrayList<>(Arrays.asList(2)));
        cardValues.put(Rank.THREE, new ArrayList<>(Arrays.asList(3)));
        cardValues.put(Rank.FOUR, new ArrayList<>(Arrays.asList(4)));
        cardValues.put(Rank.FIVE, new ArrayList<>(Arrays.asList(5)));
        cardValues.put(Rank.SIX, new ArrayList<>(Arrays.asList(6)));
        cardValues.put(Rank.SEVEN, new ArrayList<>(Arrays.asList(7)));
        cardValues.put(Rank.EIGHT, new ArrayList<>(Arrays.asList(8)));
        cardValues.put(Rank.NINE, new ArrayList<>(Arrays.asList(9)));
        cardValues.put(Rank.TEN, new ArrayList<>(Arrays.asList(10)));
        cardValues.put(Rank.JACK, new ArrayList<>(Arrays.asList(10)));
        cardValues.put(Rank.QUEEN, new ArrayList<>(Arrays.asList(10)));
        cardValues.put(Rank.KING, new ArrayList<>(Arrays.asList(10)));
    }
}
