package model;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int marker;
    private int loc;

    public Deck() {
        this.cards = new Card[52];
        int pos = 0;
        for (Card.Suit suit: Card.Suit.values()){
            for (Card.Rank rank: Card.Rank.values()){
                this.cards[pos] = new Card(rank, suit);
                pos++;
        }
    }
    marker = 0;
}
    public void shuffle(){
        //Shuffles the order of the cards in this.cards 
        //Also re-introduces all 'drawn' cards back into deck
        int index;
        Card temp;
        Random r = new Random();
        for (int i = this.cards.length-1; i > 0; i--){
            index = r.nextInt(i+1);
            temp = this.cards[i];
            this.cards[i] = this.cards[index];
            this.cards[index] = temp;
        }
    }
    
    public Card drawCard() throws NullPointerException{
        //Draws "top" card off the deck, then discards the card
        if (marker == 0){
        this.shuffle();
        }
        
        if (hasNext()){
            Card res = this.cards[marker];
            marker++;
            System.out.println(res);
            return res;
        }
        return null;
    }
    public boolean hasNext(){
        //Checks if there are any more cards to draw!
        return marker < 52;
    }
    public void printCards(){
        for (Card card: this.cards){
            System.out.println(card);
        }
    }
    public int getCard(Card card){
        for (int i = 0; i < this.cards.length; i++){
            if (this.cards[i].equals(card)){
                System.out.println("The index is: " + i);
                return i;
            }
        }
        return -1;
    }
    private void swap(int ndx1, int ndx2){
        Card temp = this.cards[ndx1];
        this.cards[ndx1] = this.cards[ndx2];
        this.cards[ndx2] = temp;
    }
    public void selectionSort(){
        int pos;
        for (int i = 0; i < this.cards.length-1; i++){
            pos = i;
            for (int j = i+1; this.cards[j].getRank().value < this.cards[pos].getRank().value; j = pos){
                swap(i, j);
            }
        }
    }
    public void insertionSort(){
        
    }
    public void mergeSort(){
        
    }
}
