package model;

import logic.*;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UITest {
    InputStream sysInBackup;
    ByteArrayInputStream in;
    UI checkerGame;

    @Before
    public void before(){
        //sysInBackup = System.in; // backup System.in to restore it later
        //in = new ByteArrayInputStream("My string".getBytes());
        //System.setIn(in);
        checkerGame = UI.getInstance();

    }

    @Test
    public void testHandleInput(){

    }

    @Test
    public void testStartUp(){

    }

    @Test
    public void testConvertToGame(){

    }

    @Test
    public void testConvertToStrategy(){
        assertEquals(HumanPlayer.class.getSimpleName(), UI.convertToStrategy("HumanPlayer").getClass().getSimpleName());
        assertEquals(RandomPlayer.class.getSimpleName(), UI.convertToStrategy("RandomPlayer").getClass().getSimpleName());
        assertEquals(MinMaxPlayer.class.getSimpleName(), UI.convertToStrategy("MinMaxPlayer").getClass().getSimpleName());

    }

    @Test //assume input has already been checked for validity
    public void testConvertInputToXY(){
        String testString1 = "a1xb2xc3";
        String testString2 = "c5xd6";

        ArrayList<Move> Move1 = new ArrayList();
        ArrayList<Move> Move2 = new ArrayList();

        Move newMove1 = new Move(0,0,1,1);
        Move newMove2 = new Move(1,1,2,2);
        Move newMove3 = new Move(2,4,3,5);

        Move1.add(newMove1);
        Move1.add(newMove2);
        Move2.add(newMove3);

        ArrayList<Move> actual1 = UI.convertInputToXY(testString1);
        ArrayList<Move> actual2 = UI.convertInputToXY(testString2);

        assertEquals(Move1.toString(), actual1.toString());
        assertEquals(Move2.toString(), actual2.toString());
    }

    @Test
    public void testCheckInputIsValid(){
        assertTrue(UI.checkInputIsValid("a2xb3"));
        assertTrue(UI.checkInputIsValid("a2xb3xc4"));
        assertTrue(UI.checkInputIsValid("a2xb3xc4xb5"));


        assertFalse(UI.checkInputIsValid("Hello"));
        assertFalse(UI.checkInputIsValid("HumanPlayer"));
        assertFalse(UI.checkInputIsValid("asdf6sadf6"));
        assertFalse(UI.checkInputIsValid("a23xb3"));
    }

    @Test
    public void testOutputMoveToConsole(){

    }

    @Test
    public void testGetCharForNumber(){
        //int i;
        //for (i=0; i<27; i++);
        //{
        //    assertEquals(String.valueOf((char) (i + 'A')), UI.getCharForNumber(i));
        //}
    }

    @Test
    public void testReturnPlayerTurn(){
        assertEquals("Red Players Turn", UI.returnPlayerTurn(true));
        assertEquals("White Players Turn", UI.returnPlayerTurn(false));
    }

    @Test
    public void testGameFinishedNoMoreMoves(){

    }

    @Test
    public void testGameFinishedNoMorePieces(){

    }

    @Test
    public void testDisplayAmountOfMoves(){

    }


    @After
    public void after(){
        //System.setIn(sysInBackup);
    }

}
