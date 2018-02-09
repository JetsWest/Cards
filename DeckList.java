/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author Rick
 */
public class DeckList {
    private ArrayList<Card> card = new ArrayList<Card>();
    
    public DeckList(){
        for (Card.Suit suit: Card.Suit.values()){
            for (Card.Rank rank: Card.Rank.values()){
                card.add(new Card(rank, suit));
            }
        }
    }
    public void shuffle(){}
    public Card drawCard(){}
    public boolean hasNext(){ }
    public void printCards(){
        for (Card print: this.card){
            System.out.println(print);
        }
    }
}
