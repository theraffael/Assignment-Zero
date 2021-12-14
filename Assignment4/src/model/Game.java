package model;

import java.sql.Array;
import java.util.*;

import model.UI;

public class Game {
    /**
     * Boolean value if Player wants to continue Game
     */
    boolean continueGame = true;
    boolean continueRound;
    private Dealer dealer;
    private Deck deck;
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private int agentCount;


    public Game(){
       this.dealer = new Dealer();
       this.deck = new Deck();
       deck.shuffleDeck();
    }

    /**
     * Public method, implementation of running Game
     */
    public void runGame(){
        //UI call Game introduction
        UI.welcomeMessage();
        this.getPlayerListFromUserInput();

        Scanner userInput = new Scanner(System.in);

        while (continueGame){
            int playerBet; //todo: make this work for multiple players


            this.agentCount = playerList.size() + 1;

            if (deck.percentageOfCardsLeft() <= 50) {
                deck.shuffleDeck();
                System.out.println(UI.shuffleMessage());
            }

            for (Player player : playerList){
                System.out.println(player.getPlayerName() + " You have " + player.getMoneyAmount()+ ", how high is your bet?");
                player.updateBettingAmount(userInput.nextInt());
                if (player.getBettingAmount() > player.getMoneyAmount()){
                    System.out.println(UI.kickedOutMessage());
                    break;
                }
            }

            //beginning card draw
            this.beginningRoundCardDraw();
            int stayCount = 0;
            continueRound = true;
            while (continueRound){

                //iterate over playerList option for more players
                for(Player player : playerList){
                    if(player.isStay()){
                        break;
                    }
                    //todo: move this to UI
                    System.out.println("Your hand: ");
                    UI.outputCards(player.handCardsToString());
                    System.out.println(UI.playerDeckValueMessage(player.totalHandValue()));
                    if (dealer.handCards.size() == 0) {
                        System.out.println("[]");
                    }
                    else {
                        System.out.println("Dealer hand: " + dealer.firstHandCardsToString() + " and [HIDDEN]");
                    }

                    //ask player if they are hitting or staying
                    Call playerCall = player.hitOrStay();

                    if(playerCall == Call.HIT){
                        player.addCardToHand(deck.next());
                        //todo: move this to UI
                        System.out.println("You draw the following card: " + player.handCards.get(player.handCards.size()-1).toString());

                        if (player.totalHandValue() > 21){
                            System.out.println("You are busted. Your hand is valued at "+ player.handValue);
                            player.deductMoney(player.getBettingAmount());
                            continueRound = false;
                        }
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

            this.endOfRoundCleanup();

            //reveal dealers card
            System.out.println("dealer's cards: " + dealer.handCardsToString());
            if((dealer.totalHandValue() > playerList.get(0).totalHandValue()) && continueRound){
                System.out.println("Dealer won.");
                playerList.get(0).deductMoney(playerList.get(0).getBettingAmount());
                continueRound = false;
            }

            System.out.println(UI.dealerDeckValueMessage(dealer.totalHandValue()));
            if ((dealer.totalHandValue() > 21) && continueRound){
                System.out.println("You won!");
                playerList.get(0).addMoney(playerList.get(0).getBettingAmount());
                continueRound = false;
            }

            if ((playerList.get(0).totalHandValue() == dealer.totalHandValue()) && continueRound){
                System.out.println("Push");
                continueRound=false;
            }

            if((playerList.get(0).totalHandValue() > dealer.totalHandValue()) && continueRound){
                System.out.println("You won!");
                playerList.get(0).addMoney(playerList.get(0).getBettingAmount());
                continueRound = false;
            }

            //playerDeck.moveAllCardsBackToDeck(playingDeck);
            //dealer.handCards.moveAllCardsBackToDeck(playingDeck);
            System.out.println("End of Hand");
        }
    }

    private void endOfRoundCleanup(){
        for (Player player : playerList){
            player.resetCall();
            player.resetHandCards();
            player.resetHandValue();
        }
        dealer.resetCall();
        dealer.resetHandCards();
        dealer.resetHandValue();
    }



    private void getPlayerListFromUserInput(){
        int amount = UI.playerAmountMessage();
        for(int i=0;i<amount;i++){
            String playerName = UI.playerName();
            int moneyAmount = UI.playerMoneyAmount();
            playerList.add(new Player(moneyAmount, playerName));
        }
    }

    private void beginningRoundCardDraw(){
        for(int i=0;i<=1;i++){
            for (Player player : playerList){
                player.addCardToHand(deck.next());
            }
            dealer.addCardToHand(deck.next());
        }
    }
}
