package model;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractAgent {
    protected ArrayList<Card> handCards;
    protected ArrayList<Integer> handValue;

    abstract Call hitOrStay();

    protected int largestHandValue(){
        return Collections.max(handValue);
    }
}
