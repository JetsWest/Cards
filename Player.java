/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*; 

public class Player {
    private String name;
    private int wins;
    private int losses;
    private int points;
    private Card card;
    private Scanner sc = new Scanner(System.in);
    ArrayList<Card> hand = new ArrayList<>();
    
    Deck deck = new Deck();
    
    public Player(String playerName){
        this.name = playerName;
        this.points = 0;
        this.wins = 0;
        this.losses = 0;
    }
    public void takeCard(ArrayList<Card> currentHand){
        currentHand.add(deck.drawCard());
    }
    public boolean HitOrPass(){
        if (busted(hand)){
            System.out.println("You cant draw!");
            return false;
        }
        System.out.println("Wanna draw?");
        return this.sc.nextLine().equalsIgnoreCase("yes");
    }
    public void playRound(){
        takeCard(hand);
        getHandValue(hand);
        checkWin(hand);
    }
    public boolean checkWin(ArrayList<Card> currentHand){
        if (getHandValue(currentHand) < 21){
            return true;
        }
        return false;
    }
    public boolean busted(ArrayList<Card> currentHand){
        if (getHandValue(currentHand) > 21){
            return true;
        }else{
            return false;
        }
    }
    public int getHandValue(ArrayList<Card> currentHand){
        for (int i = 0; i < currentHand.size(); i++){
            points += currentHand.get(i).getRank().value;
        }
        return points;
    }
    public void clearHand(ArrayList<Card> currentHand){
        currentHand.clear();
    }
    public int points(int score){
        this.points = score;
        return this.points;
    }
    public int wins(int win){
        this.wins = win;
        return this.wins;
    }
    public int losses(int lose){
        this.losses = lose;
        return this.losses;
    }
    public String name(){
        return this.name;
    }
    public List<Card> hand(ArrayList<Card> currentHand){
        for (int i = 0; i < currentHand.size(); i++){
            System.out.println("Position " + (i+1) + ": " + currentHand.get(i));
        }
        return currentHand;
    }
}
