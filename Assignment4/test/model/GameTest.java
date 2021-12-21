package model;

import model.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class GameTest {
    @BeforeEach
    public void init(){
        boolean continueGame = true;
        boolean continueRound;
        final Dealer dealer = new Dealer();
        final Deck deck = new Deck();
        ArrayList<Player> playerList = new ArrayList<>();
        int agentCount;
        int stayCount;

        deck.shuffleDeck();
    }


}
