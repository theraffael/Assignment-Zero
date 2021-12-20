package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @BeforeEach
    public void init(){
        Deck d = new Deck();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void testPercentageOfCardsLeft(int numberOfDecks) {
        Deck d = new Deck(numberOfDecks);
        assertEquals(100, d.percentageOfCardsLeft());

        for (int i = 0; i < 26 * numberOfDecks; i ++){
            d.next();
        }
        assertEquals(50, d.percentageOfCardsLeft(), String.valueOf(d.percentageOfCardsLeft()));

        for (int i = 0; i < 13 * numberOfDecks; i ++){
            d.next();
        }
        assertEquals(25, d.percentageOfCardsLeft(), String.valueOf(d.percentageOfCardsLeft()));

        for (int i = 0; i < 13 * numberOfDecks; i ++){
            d.next();
        }
        assertEquals(0, d.percentageOfCardsLeft(), String.valueOf(d.percentageOfCardsLeft()));

        d.shuffleDeck();
        assertEquals(100, d.percentageOfCardsLeft());
    }


    @Test
    public void testHasNext() {
        Deck d = new Deck();
        for (int i = 0; i < 52; i ++){
            d.next();
        }
        assertFalse(d.hasNext());
        assertNull(d.next());
    }


    @Test
    public void textShuffleDeck() {
        Deck d = new Deck();
        int sameCardDrawn = 0;

        // draw random card
        Card c1 = d.next();

        // shuffle deck 100 times and count how often same card is drawn
        for (int i = 0; i < 100; i ++){
            d.shuffleDeck();
            Card c2 = d.next();
            if (c1.equals(c2)){
                sameCardDrawn++;
            }
        }

        // Check if same card is always drawn
        assertTrue(sameCardDrawn != 100, String.valueOf(sameCardDrawn));
        // Check if same card is drawn more than ten times (possible, but highly unlikely)
        assertTrue(sameCardDrawn <= 10, String.valueOf(sameCardDrawn));
    }

    @Test
    public void testToString(){
        Deck d = new Deck();

        assertEquals(String.class, d.toString().getClass());
    }

    @Test
    public void testRemoveCard(){
        Deck d = new Deck();
        assertEquals(52, d.deckSize());
        d.removeCard(1);
        assertEquals(51, d.deckSize());
    }

    @Test
    public void testCardsTotalValue(){ //todo: test if aces are correctly counted as one or eleven
        //creating new deck which is initialized as a "full deck"
        Deck d = new Deck();
        assertEquals(340, d.cardsTotalValue());

        //empty the whole deck
        while (d.deckSize() > 0){
            d.removeCard(0);
        }

        //making sure that it is empty
        assertEquals(0, d.cardsTotalValue());

        /*adding a some card of each suit to the deck and after each
        checking whether the total value of the deck is still correct */
        d.addCard(new Card(Rank.FIVE, Suit.CLUBS));
        assertEquals(5, d.cardsTotalValue());

        d.addCard(new Card(Rank.EIGHT, Suit.DIAMONDS));
        assertEquals(13, d.cardsTotalValue());

        d.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
        assertEquals(23, d.cardsTotalValue());

        d.addCard(new Card(Rank.ACE, Suit.SPADES));
        assertEquals(24, d.cardsTotalValue());
    }

    @Test
    public void testMoveAllCardsBackToDeck(){
        Deck d = new Deck();
        Deck e = new Deck();

        while (d.deckSize() > 0){
            d.removeCard(0);
        }
        while (e.deckSize() > 0){
            e.removeCard(0);
        }
        e.addCard(new Card(Rank.EIGHT, Suit.SPADES));

        assertEquals(0, d.deckSize());
        assertEquals(1, e.deckSize());

        e.moveAllCardsBackToDeck(d);
        assertEquals(1, d.deckSize());
        assertEquals(0, e.deckSize());
    }

}