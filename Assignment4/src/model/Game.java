package model;

import java.util.ArrayList;

public class Game {
    /**
     * Boolean value if Player wants to continue Game
     */
    boolean continueGame;
    boolean continueRound;
    private Dealer dealer;
    private Deck deck;
    private ArrayList<Player> playerList;
    private int agentCount;

    public Game(){
       this.dealer = new Dealer();
       playerList.add(new Player(100, "placeHolder"));
       this.agentCount = playerList.size() + 1;
       this.deck = new Deck();
       deck.shuffleDeck();
    }

    /**
     * Public method, implementation of running Game
     */
    public void runGame(){
        //UI call Game introduction

        while (continueGame){

            if (deck.percentageOfCardsLeft() <= 50) {
                deck.shuffleDeck();
                // todo: UI call to let player know that deck was shuffled
            }

            int stayCount = 0;
            continueRound = true;
            while (continueRound){
                //beginning card draw
                this.beginningRoundCardDraw();

                //iterate over playerList option for more players
                for(Player player : playerList){
                    //ask player if they are hitting or staying
                    Call playerCall = player.hitOrStay();
                    if(playerCall == Call.HIT){
                        player.addCardToHand(deck.next());
                    }
                    else{
                        stayCount++;
                    }
                }

                //dealer hits if less than 17 points
                Call dealerCall = dealer.hitOrStay();
                if(dealerCall == Call.HIT){
                    dealer.addCardToHand(deck.next());
                }
                else{
                    stayCount++;
                }

                //if amount of stays are equal to agents on table stop round
                if(stayCount == this.agentCount){
                    continueRound = false;
                }
            }
        }
    }

    private void beginningRoundCardDraw(){
        for(int i=0;i==1;i++){
            for (Player player : playerList){
                player.addCardToHand(deck.next());
            }
            dealer.addCardToHand(deck.next());
        }
    }
}
