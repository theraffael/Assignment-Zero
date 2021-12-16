package model;

import model.Player;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class PlayerTest {

    @Test
    void largestHandValueTest() {
        Player player = new Player(100, "testPlayer");
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        player.addCardToHand(card4);

        assertTrue(player.largestHandValue() == 17);
        assertTrue(player.handValue.toString().equals("[17, 27]"));
    }
}