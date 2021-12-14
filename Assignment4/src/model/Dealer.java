package model;

import java.util.ArrayList;

public class Dealer extends AbstractAgent{

    @Override
    Call hitOrStay() {
        if(this.largestHandValue() < 17){
            this.call = Call.HIT;
            return Call.HIT;
        }
        else{
            this.call = Call.HIT;
            return Call.STAY;
        }
    }

    public String handCardsToString(){
        ArrayList<String> cardStrings = new ArrayList<>();
        for (Card card : handCards){
            cardStrings.add(card.getString());
        }
        return cardStrings.toString();
    }

    public String firstHandCardsToString(){
        return handCards.get(0).getString();
    }
}
