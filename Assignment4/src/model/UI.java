package model;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class UI {

    public static Scanner keyBoard = new Scanner(System.in);

    //Singleton pattern
    private static final UI instance = new UI();

    private UI(){}

    public static UI getInstance(){return instance;}



    public static void welcomeMessage(){
        System.out.println(" __       __            __                                                      __                      _______   __                      __                                    __        __");
        System.out.println("/  |  _  /  |          /  |                                                    /  |                    /       \\ /  |                    /  |                                  /  |      /  |");
        System.out.println("$$ | / \\ $$ |  ______  $$ |  _______   ______   _____  ____    ______         _$$ |_     ______        $$$$$$$  |$$ |  ______    _______ $$ |   __      __   ______    _______ $$ |   __ $$ |");
        System.out.println("$$ |/$  \\$$ | /      \\ $$ | /       | /      \\ /     \\/    \\  /      \\       / $$   |   /      \\       $$ |__$$ |$$ | /      \\  /       |$$ |  /  |    /  | /      \\  /       |$$ |  /  |$$ |");
        System.out.println("$$ /$$$  $$ |/$$$$$$  |$$ |/$$$$$$$/ /$$$$$$  |$$$$$$ $$$$  |/$$$$$$  |      $$$$$$/   /$$$$$$  |      $$    $$< $$ | $$$$$$  |/$$$$$$$/ $$ |_/$$/     $$/  $$$$$$  |/$$$$$$$/ $$ |_/$$/ $$ |");
        System.out.println("$$ $$/$$ $$ |$$    $$ |$$ |$$ |      $$ |  $$ |$$ | $$ | $$ |$$    $$ |        $$ | __ $$ |  $$ |      $$$$$$$  |$$ | /    $$ |$$ |      $$   $$<      /  | /    $$ |$$ |      $$   $$<  $$/");
        System.out.println("$$$$/  $$$$ |$$$$$$$$/ $$ |$$ \\_____ $$ \\__$$ |$$ | $$ | $$ |$$$$$$$$/         $$ |/  |$$ \\__$$ |      $$ |__$$ |$$ |/$$$$$$$ |$$ \\_____ $$$$$$  \\     $$ |/$$$$$$$ |$$ \\_____ $$$$$$  \\  __");
        System.out.println("$$$/    $$$ |$$       |$$ |$$       |$$    $$/ $$ | $$ | $$ |$$       |        $$  $$/ $$    $$/       $$    $$/ $$ |$$    $$ |$$       |$$ | $$  |    $$ |$$    $$ |$$       |$$ | $$  |/  |");
        System.out.println("$$/      $$/  $$$$$$$/ $$/  $$$$$$$/  $$$$$$/  $$/  $$/  $$/  $$$$$$$/          $$$$/   $$$$$$/        $$$$$$$/  $$/  $$$$$$$/  $$$$$$$/ $$/   $$/__   $$ | $$$$$$$/  $$$$$$$/ $$/   $$/ $$/ ");
        System.out.println("                                                                                                                                                 /  \\__$$ |");
        System.out.println("                                                                                                                                                 $$    $$/");
        System.out.println("                                                                                                                                                  $$$$$$/      ");
    }

    public static int playerAmountMessage(){
        System.out.println("Enter Amount of Players");
        while (true) {
            try{
                return Integer.parseInt(keyBoard.nextLine());
            }
            catch (Exception e){
                System.out.println("Enter an integer number.");
            }
        }
    }
    public static String playerName(){
        System.out.println("Enter Name of Player");
        return keyBoard.nextLine();
    }

    public static Boolean wantToContinuePlaying(String playerName){
        System.out.println(playerName + " would you like to continue playing? Y/N");
        boolean bool = false;
        while (!bool){
            String nextLine = keyBoard.nextLine().toLowerCase();
            if (nextLine.equals("y") || nextLine.equals("n")){
                bool = true;
                if (nextLine.equals("y") ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                System.out.println("Please return Y or N");
            }
        }
        return null;
    }

    public static int playerMoneyAmount() {
        System.out.println("Enter Money Amount");
        while (true) {
            try{
                return Integer.parseInt(keyBoard.nextLine());
            }
            catch (Exception e){
                System.out.println("Enter a dollar amount.");

            }
        }
    }
    public static int playerBettingAmount(Player player){
        System.out.println(player.getPlayerName() + " You have " + player.getMoneyAmount()+ ", how high is your bet?");
        while (true) {
            try{
                return Integer.parseInt(keyBoard.nextLine());
            }
            catch (Exception e){
                System.out.println("Enter a dollar amount.");
            }
        }
    }

    public static Call playerHitOrStay(String playerName){
        System.out.println(playerName + " would you like to Hit or Stay");
        String response = keyBoard.nextLine();
        String lowerCase = response.toLowerCase(Locale.ROOT);
        if (lowerCase.equals("hit")){
            return Call.HIT;
        }
        else if (lowerCase.equals("stay")){
            return Call.STAY;
        }
        else{
            return null;
        }
    }

    public static void outputCards(String handCards){
        System.out.println(handCards);
    }

    public static String shuffleMessage(){
        return "The playing deck has been shuffled.";
    }

    public static void noMorePlayersInLobbyMessage(){
        System.out.println("No more players in lobby, game is over");
    }

    public static String amountMessage(){
        return ("");
    }

    public static String kickedOutMessage(){
        return "You cannot bet more than you have. You are requested to leave our establishment.";
    }

    public static String playerDeckValueMessage(String handValues){
        return "Your hand value is: " + handValues;
    }

    public static String dealerDeckValueMessage(String deckValue){
        return "dealer's hand value is: " + deckValue;
    }
}