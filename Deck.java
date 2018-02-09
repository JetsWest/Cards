package model;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int marker;
    
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
        Random rand = new Random();
        for (int i = 0; i < cards.length; i++){
            index = rand.nextInt(i+1);
            Card temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }
    
    public Card drawCard(){
        //Draws "top" card off the deck, then discards the card
        if (marker == 0){
        this.shuffle();
        }
        if (canDraw()){
            Card res = this.cards[marker];
            marker++;
            System.out.println(res);
            return res;
        }
        return null;
    }
    public boolean canDraw(){
        //Checks if there are any more cards to draw!
        return marker < 52;
    }
    public void printCards(){
        for (Card card: this.cards){
            System.out.println(card);
        }
    }
}
