/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Scanner;
import model.Deck;

/**
 *
 * @author atkins4440j
 */
public class Game {
    private Deck deck;
    private Scanner sc = new Scanner(System.in);
    
    public Game(){
        this.deck = new Deck();
        this.deck.shuffle();
    }
    public Deck getDeck(){
        return this.deck;
    }
    public void run(){
        
    }
}
