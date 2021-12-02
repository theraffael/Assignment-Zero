package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void testPercentageOfCardsLeft(int numberOfDecks) {
        Deck d = new Deck(numberOfDecks);
        assertTrue(d.percentageOfCardsLeft() == 100);

        for (int i = 0; i < 26 * numberOfDecks; i ++){
            d.next();
        }
        assertTrue(d.percentageOfCardsLeft() == 50, String.valueOf(d.percentageOfCardsLeft()));

        for (int i = 0; i < 13 * numberOfDecks; i ++){
            d.next();
        }
        assertTrue(d.percentageOfCardsLeft() == 25, String.valueOf(d.percentageOfCardsLeft()));

        for (int i = 0; i < 13 * numberOfDecks; i ++){
            d.next();
        }
        assertTrue(d.percentageOfCardsLeft() == 0, String.valueOf(d.percentageOfCardsLeft()));

        d.shuffleDeck();
        assertTrue(d.percentageOfCardsLeft() == 100);
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

}