package model;

/**
 * Implements Player functionality
 */
public class Player extends AbstractAgent {
    private int moneyAmount;
    private String playerName;
    private int bettingAmount;

    public Player(int startingMoneyAmount, String playerName){
        this.moneyAmount = startingMoneyAmount;
        this.playerName = playerName;
    }

    /**
     * Public method, makes call to UI for Input
     */
    @Override
    public Call hitOrStay() {
        return UI.playerHitOrStay(this.playerName);
    }

    /**
     * Public method, Implements money deduction
     */
    public void roundLoss(){
        this.deductMoney(bettingAmount);
    }

    public void initBettingAmount(int bettingAmount){
        if(bettingAmount>0 && bettingAmount<= this.moneyAmount) {
            this.bettingAmount = bettingAmount;
        }
    }

    public int getMoneyAmount(){
        return moneyAmount;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getBettingAmount(){return bettingAmount;}

    public void updateBettingAmount(int x){ this.bettingAmount += x;}

    public void deductMoney(int amount){
        this.moneyAmount = this.moneyAmount - amount;
    }

    public void addMoney(int amount){
        this.moneyAmount = this.moneyAmount + amount;
    }
}
