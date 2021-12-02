package model;

public class Dealer extends AbstractAgent{

    @Override
    Call hitOrStay() {
        if(this.largestHandValue() < 17){
            return Call.HIT;
        }
        else{
            return Call.STAY;
        }
    }
}
