package model;

public class Game {
    /**
     * Boolean value if Player wants to continue Game
     */
    boolean continueGame;
    private Dealer dealer;
    private Player player;

    public Game(){
       this.dealer = new Dealer();
       this.player = new Player(100, "placeHolder");
    }

    /**
     * Public method, implementation of running Game
     */
    public void runGame(){
        //UI call Game introduction
        while (continueGame){
            
        }
    }
}
