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
        Deck TestDeck = new Deck();
        TestDeck.shuffle();
        TestDeck.mergeSort();
        TestDeck.printCards();
    }
}
