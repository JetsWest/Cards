/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.Scanner;
import model.Deck;
import org.boof.Output;
import org.boof.ListItemInput;
import org.boof.NumberInput;
/**
 *
 * @author atkins4440j
 */
public class Game {
    private Deck deck;
    private static ArrayList<Player> players;
    private Scanner sc = new Scanner(System.in);
    
    public Game(){
        this.deck = new Deck();
        this.deck.shuffle();
        this.players = new ArrayList<>();
    }
    public Deck getDeck(){
        return this.deck;
    }
    public void run(){
        NumberInput numHumanPlayers = new NumberInput();
        numHumanPlayers.setPrompt("How many human players do you want?");
        numHumanPlayers.setNumberType("Integer");
        
        NumberInput numComputerPlayers = new NumberInput();
        numComputerPlayers.setPrompt("How many computer players do you want?");
        numComputerPlayers.setNumberType("Integer");
        
        System.out.println("Welcome to the 21 game!");
        ListItemInput wantToPlay = new ListItemInput();
        wantToPlay.setPrompt("Want to continue?");
        wantToPlay.add("y", "Yes");
        wantToPlay.add("n", "No");
        boolean playing = true;
        numHumanPlayers.run();
        numComputerPlayers.run();
        for (int i = 0; i < (int)numHumanPlayers.get(); i++){
            this.players.add(new Player());
            this.players.get(i).takeCard(deck.drawCard());
            this.players.get(i).takeCard(deck.drawCard());
        }
        for (int i = 0; i < (int)numComputerPlayers.get(); i++){
            this.players.add(new ComputerPlayer(ComputerPlayer.HARD));
            this.players.get(i+(int)numHumanPlayers.get()).takeCard(deck.drawCard());
            this.players.get(i+(int)numHumanPlayers.get()).takeCard(deck.drawCard());
            this.players.get(i + (int) numHumanPlayers.get()).peek();
        }
        while(playing){
            wantToPlay.run();
            if (wantToPlay.getKey().equalsIgnoreCase("y")){
                for (Player player : players){
                    player.play(this.deck);
                }
                for (int i = 0; i < players.size()-1; i++){
                checkWinner(players.get(i), players.get(i+1));
                }
            }else{
                System.out.println("Thanks for playing!");
                playing = false;
            }
        }

    }
    public void checkWinner(Player player, Player dealer) {
        if (player.getHandValue() <= 21 && player.getHandValue() > dealer.getHandValue()){
            System.out.println("You win!");
            player.win();
            dealer.lose();
        }else if (dealer.getHandValue() <= 21 && dealer.getHandValue() > player.getHandValue()){
            System.out.println("You lose dealer wins!");
            dealer.win();
            player.lose();
        }else{
            System.out.println("It is a tie!");
        }
    }
    public static void main(String[] args){
        Game game = new Game();
        game.run();
    }
}
