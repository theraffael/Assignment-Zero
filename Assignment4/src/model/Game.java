package model;

import java.util.*;

public class Game {
    /**
     * Boolean value if Player wants to continue Game
     */
    boolean continueGame = true;
    boolean continueRound;
    private final Dealer dealer;
    private final Deck deck;
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private int agentCount;
    private int stayCount;


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
                }
                if (!player.checkIfPlayerHasSufficientFunds()){
                    System.out.println(player.getPlayerName() + " You have insufficient funds" + player.getMoneyAmount());
                    System.out.println(UI.kickedOutMessage());
                    playerList.remove(player);
                }
            }

            //beginning card draw
            this.beginningRoundCardDraw();
            stayCount = 0;
            continueRound = true;
            while (continueRound){

                //iterate over playerList option for more players
                for(Player player : playerList){
                    if(player.isStay()){
                        continue;
                    }
                    //todo: move this to UI
                    System.out.println("Your hand: ");
                    UI.outputCards(player.handCardsToString());
                    System.out.println(UI.playerDeckValueMessage(player.handValue.toString()));
                    System.out.println("Dealer hand: " + dealer.firstHandCardsToString() + " and [HIDDEN]");


                    //ask player if they are hitting or staying
                    Call playerCall = player.hitOrStay();

                    if(playerCall == Call.HIT){
                        player.addCardToHand(deck.next());
                        UI.outputCards(player.handCardsToString());
                    }
                    else{
                        stayCount++;
                    }
                }

                if(!dealer.isStay()){
                    //dealer hits if less than 17 points
                    Call dealerCall = dealer.hitOrStay();
                    if(dealerCall == Call.HIT){
                        dealer.addCardToHand(deck.next());
                    }
                    else{
                        stayCount++;
                    }
                }

                this.isRoundOver();
            }

            //reveal dealers card
            System.out.println("dealer's cards: ");
            UI.outputCards(dealer.handCardsToString());
            this.decideWinningHand();
            this.endOfRoundCleanup();

            System.out.println("End of Hand");
            this.askPlayersIfWantToContinue();
        }
    }

    private void askPlayersIfWantToContinue(){
        for (Player player : playerList){
            Boolean bool = UI.wantToContinuePlaying(player.getPlayerName());
            if(bool){
                continue;
            }
            else{
                UI.kickedOutMessage();
                playerList.remove(player);
            }
            if(playerList.isEmpty()){
                UI.noMorePlayersInLobbyMessage();
                continueGame = false;
                break;
            }
        }
    }

    private void isRoundOver(){
        int bustCounter = 0;
        for (Player player : playerList){
            if(player.largestHandValue() > 21){
                bustCounter++;
            }
        }
        if(bustCounter == playerList.size()){
            continueRound = false;
        }
        //if amount of stays are equal to agents on table stop round
        if(stayCount == this.agentCount){
            continueRound = false;
        }
    }

    private void decideWinningHand(){
        for (Player player : playerList){

            System.out.println(UI.dealerDeckValueMessage(dealer.handValue.toString()));


            //if dealer has over 21 and player doesn't -> player wins
            if ((dealer.largestHandValue() > 21) && player.largestHandValue() <= 21){
                System.out.println("You won!");
                player.addMoney(player.getBettingAmount());
                continueRound = false;
            }

            //if dealer has over 21 and player aswell
            else if (player.largestHandValue() > 21 && dealer.largestHandValue() > 21){
                System.out.println("Push");
                continueRound=false;
            }

            //if player has over 21 and dealer doesn't
            else if (player.largestHandValue() > 21 && dealer.largestHandValue() <= 21){
                System.out.println("You are busted. Your hand is valued at "+ player.handValue);
                player.deductMoney(player.getBettingAmount());
                continueRound = false;
            }

            //if dealer has higher hand value and not over 21
            else if(dealer.largestHandValue() > player.largestHandValue() && dealer.largestHandValue() <= 21){
                System.out.println("Dealer won.");
                player.deductMoney(player.getBettingAmount());
                continueRound = false;
            }

            //if player hand value is equal to dealers
            else if (player.largestHandValue() == dealer.largestHandValue()){
                System.out.println("Push");
                continueRound=false;
            }

            //if player hand value is larger than dealer hand value, player wins round
            if((player.largestHandValue() > dealer.largestHandValue()) && player.largestHandValue() <= 21){
                System.out.println("You won!");
                player.addMoney(player.getBettingAmount());
                continueRound = false;
            }
        }
    }

    private void endOfRoundCleanup(){
        for (Player player : playerList){
            player.resetCall();
            player.resetHandCards();
            player.resetHandValue();
            player.resetBettingAmount();
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
