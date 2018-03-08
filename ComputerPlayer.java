/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Card;
import model.*;

/**
 *
 * @author atkins4440j
 */
public class ComputerPlayer extends Player {
    private int wins;
    private int losses;
    private int points;
    private int score;
    private int level;
    public static final int POINTS_PER_GAME = 20;
    private Scanner sc = new Scanner(System.in);
    private List<Card> hand;
    
    public ComputerPlayer(){
        this.hand = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
        this.score = 0;
        this.points = 0;
    }
    public int difficulty(){
        return this.level;
    }
    
    public void hitOrStay(){
        if (difficulty() == 1){
            if (getHandValue() < 21){
                takeCard();
            }else{
                System.out.println("The dealer has chosen to stay.");
            }
        }
        if (difficulty() == 2){
            if (getHandValue() < 20){
                takeCard();
            }else{
                System.out.println("The dealer has chosen to stay.");
            }
        }
        if (difficulty() == 3){
            if (getHandValue() < 17){
                takeCard();
            }else{
                System.out.println("The dealer has chosen to stay.");
            }
        }
    }
    public void peekaboo(){
        System.out.println(this.hand.get(0));
    }
    
}
