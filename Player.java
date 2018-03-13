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
import org.boof.ListItemInput;

public class Player {

    private int wins;
    private int losses;
    private int points;
    private int score;
    public static final int POINTS_PER_GAME = 20;
    private Scanner sc = new Scanner(System.in);
    private List<Card> hand;

    public Player() {
        this.score = 0;
        this.points = 0;
        this.hand = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
    }

    public void win() {
        this.wins++;
        this.score += POINTS_PER_GAME;
    }

    public void lose() {
        this.losses--;
        this.score -= POINTS_PER_GAME;
    }

    public void takeCard(Card card) {
        this.hand.add(card);
    }

    public boolean busted() {
        if (getHandValue() > 21) {
            return true;
        }
        return false;
    }

    public int getHandValue() {
        boolean ace = false;
        for (int i = 0; i < this.hand.size(); i++) {
            if (this.hand.get(i).getRank().value > 10) {
                points += 10;
            }
            else if (this.hand.get(i).getRank().value == 1) {
                ace = true;
            }else{
             points += this.hand.get(i).getRank().value;
            }
            if (ace == true && points + 11 <= 21) {
                points += 11;
            }  
            
        }
        System.out.println(points);
        return points;
    }

    public void clearHand() {
        this.hand.clear();
    }

    public void printHand() {
        for (int i = 0; i < this.hand.size(); i++) {
            System.out.println((i + 1) + (":") + this.hand.get(i));
        }
    }

    public void play(Deck deck) {
        boolean isDone = false;
        takeCard(deck.drawCard());
        takeCard(deck.drawCard());
        System.out.println("Here are your cards and your score:");
        printHand();
        System.out.println(getHandValue());
        ListItemInput hitOrPass = new ListItemInput();
        hitOrPass.add("h", "hit");
        hitOrPass.add("p", "pass");
        while (!isDone){
            System.out.println("Hit or pass?");
            hitOrPass.run();
        if (hitOrPass.getKey().equalsIgnoreCase("h")) {
            takeCard(deck.drawCard());
        } else {
            System.out.println("You have chosen to pass. Next player's turn.");
            isDone = true;
            }
        }
    }

    public int numWins() {
        return this.wins;
    }

    public int numLosses() {
        return this.losses;
    }

    public int numScore() {
        return this.score;
    }
}
