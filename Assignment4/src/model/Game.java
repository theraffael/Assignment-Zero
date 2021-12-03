package model;

import java.util.ArrayList;

public class Game {
    /**
     * Boolean value if Player wants to continue Game
     */
    boolean continueGame;
    private Dealer dealer;
    private Deck deck;
    private ArrayList<Player> playerList;
    private int AgentCount;

    public Game(){
       this.dealer = new Dealer();
       playerList.add(new Player(100, "placeHolder"));
       this.AgentCount = playerList.size() + 1;
       this.deck = new Deck();
       deck.shuffleDeck();
    }

    /**
     * Public method, implementation of running Game
     */
    public void runGame(){
        //UI call Game introduction
        while (continueGame){
            this.beginningRoundCardDraw();
            for(Player player : playerList){
                Call playerCall = player.hitOrStay();
                if(playerCall == Call.HIT){
                    player.addCardToHand(deck.next());
                }
                else{

                }
            }
            Call dealerCall = dealer.hitOrStay();
            if(dealerCall == Call.HIT){
                dealer.addCardToHand(deck.next());
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
