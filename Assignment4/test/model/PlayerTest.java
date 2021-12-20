package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    Player testPlayer;
    int testMoneyAmount = 101;

    @BeforeEach
    public void init(){
        testPlayer = new Player(testMoneyAmount, "TestPlayer");
    }

    @Test
    void largestHandValueTest() {
        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.CLUBS);

        assertTrue(testPlayer.largestHandValue() == 0);

        testPlayer.addCardToHand(card1);
        testPlayer.addCardToHand(card2);
        testPlayer.addCardToHand(card3);
        testPlayer.addCardToHand(card4);

        assertTrue(testPlayer.largestHandValue() == 17);
        assertTrue(testPlayer.handValue.toString().equals("[17, 27]"));
    }

    @Test
    public void testResetHandCards(){

        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.DIAMONDS);

        testPlayer.addCardToHand(card1);
        testPlayer.addCardToHand(card2);
        testPlayer.addCardToHand(card3);
        testPlayer.addCardToHand(card4);

        testPlayer.resetHandCards();

        assertTrue(testPlayer.handCardsToString() == "[]");
    }

    @Test
    public void testResetHandValue(){

        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.FIVE, Suit.CLUBS);
        Card card3 = new Card(Rank.SEVEN, Suit.HEARTS);
        Card card4 = new Card(Rank.FOUR, Suit.DIAMONDS);

        testPlayer.addCardToHand(card1);
        testPlayer.addCardToHand(card2);
        testPlayer.addCardToHand(card3);
        testPlayer.addCardToHand(card4);

        testPlayer.resetHandValue();

        assertEquals("[]", testPlayer.handValue.toString());
    }
    @Test
    public void testRoundLoss(){
        testPlayer.updateBettingAmount(testMoneyAmount);
        testPlayer.roundLoss();
        assertEquals(0, testPlayer.getMoneyAmount());
    }

    @Test
    public void testInitBettingAmount(){
        testPlayer.initBettingAmount(100);
        assertEquals(100, testPlayer.getBettingAmount());
    }

    @Test
    public void testCheckIfPlayerHasSufficientFunds(){

        //first testing for 101, 91, ..., 11 and 1.
        int i = 10;
        while (i > 0){
            assertTrue(testPlayer.checkIfPlayerHasSufficientFunds());
            testPlayer.deductMoney(10);
            i--;
        }

        //setting amount of money to 0
        testPlayer.deductMoney(1);

        //testing for 0, -10, -20, ..., -100.
        int j = 10;
        while (j > 0){
            assertFalse(testPlayer.checkIfPlayerHasSufficientFunds());
            testPlayer.deductMoney(10);
            j--;
        }
    }

    @Test
    public void testResetBettingAmount(){
        testPlayer.updateBettingAmount(100);
        assertNotEquals(0, testPlayer.getBettingAmount());
        testPlayer.resetBettingAmount();
        assertEquals(0, testPlayer.getBettingAmount());
    }

    @Test
    public void testGetMoneyAmount(){
        assertEquals(testMoneyAmount, testPlayer.getMoneyAmount());
        //player.getMoneyAmount();
    }

    @Test
    public void testGetPlayerName(){
        assertEquals(String.class ,testPlayer.getPlayerName().getClass());
    }

    @Test
    public void testGetBettingAmount(){
        assertEquals(0, testPlayer.getBettingAmount());

        testPlayer.updateBettingAmount(100);

        assertEquals(100, testPlayer.getBettingAmount());
    }

    @Test
    public void testAddMoney(){
        assertEquals(101, testPlayer.getMoneyAmount());
        testPlayer.addMoney(100);
        assertEquals(201, testPlayer.getMoneyAmount());
    }

    @AfterEach
    void teardown(){
        testPlayer = null;
        System.gc();
    }

}