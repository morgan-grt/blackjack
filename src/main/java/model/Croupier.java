/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import blackjack.Game;
import blackjack.Rules;
import cartes.Carte;
import cartes.Paquet;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Croupier extends Player{
    protected Paquet paquet;
    
    public Croupier(int balance, String name) {
        super(balance, name);
        this.paquet = new Paquet(52);
    }
    
    @Override
    public boolean isDoubleBetPossible(int index){return false;}
    
    @Override
    public boolean isValidBet(int bet){return false;}
    
    @Override
    public void init(){
        playerHands = new LinkedList();
        PlayerHand playerHand = new PlayerHand(new Paquet(), 0, 0);
        paquet = new Paquet(52);
        playerHands.add(playerHand);
        state = State.PLAY;
    }
    
    /**
     * le croupier distribue une carte au joueur
     * @param player joueur actuel
     * @param index index de la main choisie
     */
    public void distributeCartToPlayer(Player player, int index){
        player.getHandCart(index).addCarte(paquet.getfirst());
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * le croupier se distribue une carte
     */
    public void distributeCartToMySelf(){
        getHandCart(0).addCarte(paquet.getfirst());
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * appel recursif pour demander au joueur son action
     * @param player joueur actuel
     * @param rules regles du jeu
     * @param index index de la main choisie
     */
    public void askAction(Player player, Rules rules, int index){
        rules.calculPoint(player, index);
        if (player.getState() == Player.State.PLAY && !rules.isPlayerLose(player)){
            String action = player.action();
            playerAction(player, rules, action, index);   
        }
    }
    
    /**
     * recupere l'action du joueur
     * @param playerAction action du joueur
     * @param player joueur actuel
     * @param rules les regles du blackjack
     * @param index l'index de la main
     */
    public void playerAction(Player player, Rules rules, String playerAction, int index){
        if (playerAction.equals("tirer")){
            draw(player, rules, index);
        }
        else if (playerAction.equals("doubler") && !player.isPlayerDoubleBet() 
                && !player.isPlayerSplit()){
            doubleBet(player, rules, index);
        }
        else if (playerAction.equals("split") && !player.isPlayerSplit()){
            split(player, rules, index);
        }
        else if (playerAction.equals("assurance") && !player.isPlayerSplit()){
            assurance(player, rules, index);
        }
        else if (playerAction.equals("passe") || playerAction.equals("lose")){
        }
        else{
            askAction(player, rules, index);
        }
    }
    
    /**
     * effectue l'action tirer du joueur
     * @param player joueur actuel
     * @param rules les regles du blackjack
     * @param index l'index de la main
     */
    public void draw(Player player, Rules rules, int index){
        distributeCartToPlayer(player, index);
        rules.calculPoint(player, index);
        askAction(player, rules, index);
    }
    
    /**
     * double la mise du joueur
     * @param player joueur actuel
     * @param rules les regles du blackjack
     * @param index l'index de la main
     */
    public void doubleBet(Player player, Rules rules, int index){
        if (rules.isDoubleBetPossible(player, index)){
            distributeCartToPlayer(player, index);
            player.playerDoubleBet();
            playerAction(player, rules, "passe", index);
        }
        else{
            askAction(player, rules, index);
        }
    }
    
    /**
     * separe le jeu du joueur
     * @param player joueur actuel
     * @param rules les regles du blackjack
     * @param index l'index de la main
     */
    public void split(Player player, Rules rules, int index){
        if (rules.isSplitPossible(player)){
            splitCartToPlayer(player);
            player.playerSplit();
            askAction(player, rules, index);
        }
        else{
            askAction(player, rules, index);
        }
    }
    
    /**
     * creer une assurance pour le joueur
     * @param player joueur actuel
     * @param rules les regles du blackjack
     * @param index l'index de la main
     */
    public void assurance(Player player, Rules rules, int index){
        if (rules.isAssurancePossible(player)){
            player.setAssured();
        }
        else{
            askAction(player, rules, index);
        }
    }
    
    public void setPaquet(Paquet paquet){
        this.paquet = paquet;
    }
    
    public Paquet getPaquet(){
        return paquet;
    }
    
    /**
     * retourne le second type apres Player
     * @return le second type
     */
    @Override
    public String myType() {
        return "Croupier";
    }

    @Override
    public int bet() {return 0;}

    @Override
    public boolean setAsValue(int index) {return false;}

    /**
     * divise la main du joueur en deux mains
     * @param player joueur actuel
     */
    public void splitCartToPlayer(Player player) {
        Carte carte = player.getPlayerHands().get(0).getHandCart().getPaquet().getFirst();
        PlayerHand playerHand = new PlayerHand(new Paquet(carte), 0, 0);
        player.getPlayerHands().add(playerHand);
    }

    @Override
    public String action() {return "";}

    
}
