package model;

import java.util.Scanner;

public class UI {

    public static Scanner keyBoard = new Scanner(System.in);

    //Singleton pattern
    private static final UI instance = new UI();

    private UI(){}

    public static UI getInstance(){return instance;}



    public static String welcomeMessage(){
        return "Welcome to Blackjack!";
    }

    public static String shuffleMessage(){
        return "The playing deck has been shuffled.";
    }

    public static String amountMessage(){
        return ("");
    }

    public static String kickedOutMessage(){
        return "You cannot bet more than you have. You are requested to leave our establishment.";
    }

    public static String playerDeckValueMessage(int deckValue){
        return "Your deck's value is: " + deckValue;
    }

    public static String dealerDeckValueMessage(int deckValue){
        return "dealer's hand value is: " + deckValue;
    }
}
