/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.List;
import model.*; 

public class Player {
    private String name;
    private int wins;
    private int losses;
    private int points;
    private Card card;
    List<Card> hand = new ArrayList<>();
    
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
    public void pass(){}
    public void playRound(){
        deck.shuffle();
    }
    public int getHandValue(ArrayList<Card> currentHand){
        int score = 0;
        for (int i = 0; i < currentHand.size(); i++){
            score += currentHand.get(i).getRank().value;
        }
        return score;
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
    public List<Card> hand(){}
}
