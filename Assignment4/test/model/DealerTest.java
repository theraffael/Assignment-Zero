package model;

import model.Dealer;
import model.Player;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerTest {
    Dealer mockDealer = new Dealer();

    @Test
    public void testInitial(){
        assertEquals("[]", mockDealer.handValue.toString());
        assertEquals("[]", mockDealer.handCards.toString());
    }

    @Test
    public void testAddCardToHand(){
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new Card(Rank.FIVE, Suit.HEARTS);

        mockDealer.addCardToHand(card1);
        mockDealer.addCardToHand(card2);

        assertEquals("[6, 16]", mockDealer.handValue.toString());
        assertEquals("[ACE of CLUBS, FIVE of HEARTS]", mockDealer.handCardsToString());
    }

    @Test
    void largestHandValueTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);

        assertTrue(dealer.largestHandValue() == 0);

        dealer.addCardToHand(card1);
        dealer.addCardToHand(card2);
        dealer.addCardToHand(card3);
        dealer.addCardToHand(card4);

        assertTrue(dealer.largestHandValue() == 17);
        assertTrue(dealer.handValue.toString().equals("[17, 27]"));
    }
}
