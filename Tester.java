/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import model.*;
/**
 *
 * @author atkins4440j
 */
public class Tester {
    public static void main(String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        deck.mergeSort();
        deck.binarySearch(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        deck.printCards();
    }
}
