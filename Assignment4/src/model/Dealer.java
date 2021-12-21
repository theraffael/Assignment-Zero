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

    public String firstHandCardsToStringFancy(){
        ArrayList<String> hiddenCard = new ArrayList<>();
        hiddenCard.add("┌─────────┐\t");
        hiddenCard.add("│░░░░░░░░░│\t");
        hiddenCard.add("│░░░░░░░░░│\t");
        hiddenCard.add("│░░░░░░░░░│\t");
        hiddenCard.add("│░░░░░░░░░│\t");
        hiddenCard.add("│░░░░░░░░░│\t");
        hiddenCard.add("└─────────┘\t");

        String firstHand = "";
        int numberOfIndividualParts = 7;
        for (int i = 0; i < numberOfIndividualParts; i++ ){
            firstHand += handCards.get(0).getFancyList().get(i);
            firstHand += hiddenCard.get(i);

            firstHand +="\n";
        }
        return firstHand;
    }
}
