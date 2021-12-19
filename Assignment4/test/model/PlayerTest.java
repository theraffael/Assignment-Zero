package model;

import model.Player;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class PlayerTest {

    @Test
    void largestHandValueTest() {
        Player player = new Player(100, "testPlayer");
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);

        assertTrue(player.largestHandValue() == 0);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        player.addCardToHand(card4);

        assertTrue(player.largestHandValue() == 17);
        assertTrue(player.handValue.toString().equals("[17, 27]"));
    }

    @Test
    public void testResetHandCards(){
        Player player = new Player(100, "TestPlayer");

        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.DIAMONDS);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        player.addCardToHand(card4);

        player.resetHandCards();

        assertTrue(player.handCardsToString() == "[]");
    }

    @Test
    public void testResetHandValue(){
        Player player = new Player(100, "TestPlayer");

        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.DIAMONDS);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        player.addCardToHand(card4);

        player.resetHandValue();

        assertEquals("[]", player.handValue.toString());
    }


}