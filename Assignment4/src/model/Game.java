package model;

public class Game {
    /**
     * Boolean value if Player wants to continue Game
     */
    boolean continueGame;
    private Dealer dealer;
    private Player player;
    private Deck deck;

    public Game(){
       this.dealer = new Dealer();
       this.player = new Player(100, "placeHolder");
       this.deck = new Deck(1);
    }

    /**
     * Public method, implementation of running Game
     */
    public void runGame(){
        //UI call Game introduction
        while (continueGame){
            // shuffle deck halfway through to avoid running out of cards and so players cannot count cards ;)
            if (deck.percentageOfCardsLeft() <= 50) {
                deck.shuffleDeck();
                // todo: UI call to let player know that deck was shuffled
            }

            // how to draw cards:
            if (deck.hasNext()) {
                Card card = deck.next();
            }
            
        }
    }
}
