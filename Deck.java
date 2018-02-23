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
        for (int i = 0; i < this.cards.length-1; i++){
            int pos = i;
            for (int j = i+1; j < this.cards.length; j++){
               if (this.cards[j].getRank().value < this.cards[pos].getRank().value){
                   pos = j;
               }
                swap(pos, i);
            }
        }
    }
    public void insertionSort(){
        for (int i = 0; i < this.cards.length; i++){
            for (int k = i; k >= 1 && this.cards[k].getRank().value < this.cards[k-1].getRank().value; k--){
                swap (k, k-1);
            }
        }
    }
    public void mergeSort(){

    }
    private Card[] mergeSortR(Card[] list){
        //Stops an infinite recursion from happening
        //based on idea that list of 1 or less things is already sorted
        if (cards.length <= 1){
            return cards;
        }
        
        //Spltting lists
        Card[] left = new Card[cards.length/2]; 
        for (int i = 0; i < cards.length/2; i++){
            left[i] = cards[i];
        }
        Card[] right = new Card[cards.length-left.length];
        for (int i = left.length; i < cards.length; i++){
            right[i - left.length] = cards[i];
        }
        
        //Sorting
        left = mergeSortR(left);
        right = mergeSortR(right);
    
        //Combining
        Card[] temp = new Card[left.length+right.length]; //STEP ONE
        int newMark = 0; //STEP TWO
        int lMark = 0;
        int rMark = 0;
        
        while (lMark < left.length && rMark < right.length){
            if (left[lMark].getRank().value < right[rMark].getRank().value){
                temp[newMark++] = left[lMark++];
            }
            if (right[rMark].getRank().value < left[lMark].getRank().value){
                temp[newMark++] = right[rMark++];
            }
        }
        System.out.println(temp);
        return temp;
    }
}
