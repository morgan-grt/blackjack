/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import cartes.Carte;
import cartes.Carte.Valeur;
import java.util.EnumMap;
import java.util.Map.Entry;
import model.Croupier;
import model.Player;
import model.PlayerHand;

/**
 *regles du jeu
 */
public class Rules {
    EnumMap<Valeur, Integer> cartValue;
    protected int minBet;
    protected int maxBet;
    
    /**
     * construction de l'objet Regles
     * @param minBet mise minimale du jeu
     * @param maxBet mise maximale du jeu
    **/
    public Rules(int minBet, int maxBet) {
        cartValue = new EnumMap<Valeur, Integer>(Valeur.class);
        cartValue.put(Valeur.As ,1);
        cartValue.put(Valeur.Deux ,2);
        cartValue.put(Valeur.Trois ,3);
        cartValue.put(Valeur.Quatre ,4);
        cartValue.put(Valeur.Cinq ,5);
        cartValue.put(Valeur.Six ,6);
        cartValue.put(Valeur.Sept ,7);
        cartValue.put(Valeur.Huit ,8);
        cartValue.put(Valeur.Neuf ,9);
        cartValue.put(Valeur.Dix ,10);
        cartValue.put(Valeur.Valet ,10);
        cartValue.put(Valeur.Dame ,10);
        cartValue.put(Valeur.Roi ,10);
        cartValue.put(Valeur.As ,11);    // 2eme valeur pour As au choix
        this.minBet = minBet;
        this.maxBet = maxBet;
    }
    
    /** 
     *  Si un player effectue l'action double, il multiplie si c'est possible sa mise par 2 et retire une carte de la pioche, il passe alors en attente si il n'est pas hors jeu.
     *  @param player player effectuant la double mise
     *  @param index index de la main choisie
     *  @return le player peut doubler
    **/
    public boolean isDoubleBetPossible(Player player, int index) {
        if(player.isDoubleBetPossible(index)) {
            player.setBalance(player.getBalance() - player.getBet(index));
            player.setBet(player.getBet(index) * 2, index);
            return true;
        }
        return false;
    }
    
    /** 
     *  mise que le player souhaite effectuer, tant qu'elle n'est pas valide, elle est decremente de 1
     *  @param player joueur actuel
     *  @param bet la mise du player
    **/
    public void bet(Player player, int bet) {
        if (bet >= minBet){
            if (player.isValidBet(bet)) {
                player.setBalance(player.getBalance() - bet);
                player.setBet(bet,0);
            }
            else {
                while(!player.isValidBet(bet) && bet > minBet) {
                    bet--;
                }
                player.setBalance(player.getBalance() - bet);
                player.setBet(bet,0);
                if (player.getBalance() < 0){
                    player.getPlayerHands().get(0).loseState();
                }
            }
        }
        else if (bet < minBet){
            while (bet < minBet){
                bet++;
            }
            player.setBalance(player.getBalance() - bet);
            player.setBet(player.getBet(0) + bet,0);
        }
        if (player.getBalance() < 0){
            player.setBalance(100);
        }
    }
    
    /**
     * calcul les points actuels d'un joueur
     * @param player joueur actuel
     * @param index index de la main choisie
     */
    public void calculPoint(Player player, int index) {
        int somme = 0;
        int nombreAs = 0;
        for (Carte c : player.getHandCart(index).getPaquet()) {
            if (Valeur.As.equals(Valeur.valueOf(c.getValeur()))) {
                nombreAs++;
                if (nombreAs > player.getAsValues(index).size()){
                    player.setAsValue(index);
                }
            }
            else {
                somme += cartValue.get(Valeur.valueOf(c.getValeur()));
            }
        }
        for (Entry<Integer, Integer> entry : player.getAsValues(index).entrySet()){
            somme += entry.getValue();
        }
        player.setPoint(somme, index);
        if(index == 0 && player.getPoint(index) == 21 
                && player.getHandCart(index).getPaquet().size() == 2){
            player.getPlayerHands().get(0).blackjackState();
        }
        else if (player.getPoint(index) > 21) {
            player.getPlayerHands().get(index).loseState();
        }
    }

    /**
     * verifie si un joueur a gagne la partie
     * @param croupier le croupier
     * @param player joueur actuel
     * @param index index de la main choisie
     */
    public void testVictory(Croupier croupier, Player player, int index){
        // le croupier a perdu
        if (croupier.getPlayerHands().get(0).getState() == PlayerHand.State.LOSE) {
            //player non perdant passent en etat gagne
            if (player.getPlayerHands().get(index).getState() == PlayerHand.State.WAIT){
                player.getPlayerHands().get(index).wonState();
            }
        }
        // le croupier n'a pas perdu
        else {
            if (player.getPlayerHands().get(index).getState() == PlayerHand.State.WAIT){
                //le player a plus de point que le croupier, il passe en gagnant
                if (player.getPoint(index) > croupier.getPoint(0)) {
                    player.getPlayerHands().get(index).wonState();
                }
                else if (player.getPoint(index) == croupier.getPoint(0)) {
                    player.getPlayerHands().get(index).equalityState();
                }
                // sinon player a perdu et croupier gagne
                else{
                    player.getPlayerHands().get(index).loseState();
                }
            }
        }
    }
    
    /**
     * regarde si le joueur a fait un blackjack
     * @param player joueur actuel
     * @return si blackjack
     */
    public boolean isBlackjackPlayer(Player player){
        if (player.getPlayerHands().get(0).getState() == PlayerHand.State.BLACKJACK){
            player.setBalance(player.getBalance() + (player.getBet(0) * 3));
            return true;
        }
        return false;
    }
    
    /**
     * regarde si la main du joueur a perdu
     * @param player joueur actuel
     * @param index index de la main choisie
     * @return la main a perdu
     */
    public boolean isLoserPlayer(Player player, int index){
        return (player.getPlayerHands().get(index).getState() == PlayerHand.State.LOSE);
    }
    
    /**
     * verifie si le joueur a perdu
     * @param player joueur actuel
     * @return le joueur a perdu
     */
    public boolean isPlayerLose(Player player){
        for (int index = 0; index < player.getPlayerHands().size(); index++){
            calculPoint(player, index);
            if (isLoserPlayer(player, index)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * distribue le gain au joueur selon son etat
     * @param croupier le croupier
     * @param player joueur actuel
     * @param index index de la main choisie
     */
    public void calculBalance(Croupier croupier, Player player, int index){
        if (player.isAssured() 
                && croupier.getPlayerHands().get(0).getState() == PlayerHand.State.BLACKJACK){
            switch (player.getPlayerHands().get(index).getState()){
                case EQUALITY:
                    player.setBalance(player.getBalance() + player.getBet(index));
                    break;
                case LOSE:
                    player.setBalance(player.getBalance() + player.getBet(index));
                    break;
                default:
                    break;
            }
        }
        else if (player.isAssured() 
                && !(croupier.getPlayerHands().get(0).getState() == PlayerHand.State.BLACKJACK)){
            switch (player.getPlayerHands().get(index).getState()){
                case WON:
                    player.setBalance(player.getBalance() + (player.getBet(index) 
                            - player.getBet(index) / 2));
                    break;
                case LOSE:
                    break;
                default:
                    break;
            }
        }
        else{
            switch (player.getPlayerHands().get(index).getState()){
                case EQUALITY:
                    player.setBalance(player.getBalance() + player.getBet(index));
                    break;
                case WON:
                    player.setBalance(player.getBalance() + (player.getBet(index) * 2));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * verifie si un joueur peut spliter son jeu
     * @param player joueur actuel
     * @return le joueur peut spliter
     */
    public boolean isSplitPossible(Player player) {
        return player.isSplitPossible();
    }
    
    /**
     * effectue le split sur la balance et les mises
     * @param player joueur actuel
     */
    public void splitAction(Player player){
        if (player.isPlayerSplit()){
            for (int index = 0; index <= 1; index++){
                player.setBet(player.getBet(0),index);
                calculPoint(player, index);
            }
            player.setBalance(player.getBalance() - player.getBet(0));
        }
    } 

    /**
     * verifie si l'assurance est possible
     * @param player joueur actuel
     * @return l'assurance est possible
     */
    public boolean isAssurancePossible(Player player){
        if (player.isAssurancePossible()){
            player.setBet(player.getBet(0) + player.getBet(0) / 2 , 0);
            player.setBalance(player.getBalance() - player.getBet(0) / 2);
            return true;
        }
        return false;
    }
}
