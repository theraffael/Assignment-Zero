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
            this.call = Call.STAY;
            return Call.STAY;
        }
    }

    public String firstHandCardsToString(){
        return handCards.get(0).getString();
    }
}
