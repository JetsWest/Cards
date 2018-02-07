package model;

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
        
    }
    
    public Card drawCard(){
        //Draws "top" card off the deck, then discards the card
        //Need to SHUFFLE beforehand
        Card res = cards[marker];
        marker++;
        System.out.println(res);
        return res;
    }
    public boolean canDraw(){
        //Checks if there are any more cards to draw!
        for (int index = 0; index < cards.length; index++){
            if (cards.length < 1){
                System.out.println("Cannot draw!");
                return false;
            }
        }
        return true;
    }
    public void printCards(){
        for (Card card: this.cards){
            System.out.println(card);
        }
    }
}
