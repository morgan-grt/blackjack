/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import blackjack.Rules;
import java.util.ArrayList;
import model.Croupier;
import model.Player;
import model.PlayerHand;

/**
 * class controler, pour gerer le deroulement du jeu
 */
public class Controler {
    private final Rules rules;
    private ArrayList<Player> playerList;
    private Croupier croupier;

    public Controler(Rules rules) {
        this.rules = rules;
    }
    
    /**
     * recupere la mise du joueur 
     * @param player joueur actuel
     */
    public void setBetPlayer(Player player){
        int bet = player.bet();
        rules.bet(player, bet);
    }
    
    /**
     * parcours chaque joueur pour demander de miser
     */
    public void setBetPlayers(){
        for (Player player : playerList){
            setBetPlayer(player);
        }
    }
    
    /**
     * demande au croupier de distribuer une carte au joueur
     * @param croupier le croupier
     * @param player joueur actuel
     * @param index index de la main choisie
     */
    public void distributeCartToPlayer(Croupier croupier, Player player, int index){
        croupier.distributeCartToPlayer(player, index);
        rules.calculPoint(player, index);
    }
    
    /**
     * demande au croupier de se distribuer une carte
     * @param croupier le croupier
     */
    public void distributeCartToCroupier(Croupier croupier){
        croupier.distributeCartToMySelf();
        rules.calculPoint(croupier, 0);
    }

    /**
     * demande au croupier de distribuer une carte a chaque joueur
     * @param croupier le croupier
     */
    public void distributeCartToPlayers(Croupier croupier) {
        this.croupier = croupier;
        for (Player player : playerList){
            distributeCartToPlayer(croupier, player, 0);
        }
    }
    

    /**
     * le croupier demande a chaque joueur son action
     * @param croupier le croupier
     */
    public void action(Croupier croupier){
        for (Player player : new ArrayList<Player>(playerList)){
            player.playState();
            if (player.isPlayerSplit()){
                for (int index = 0; index < player.getPlayerHands().size(); index++){
                    if (player.getPlayerHands().get(index).getState() == PlayerHand.State.PLAYED){}
                    else{
                        croupier.askAction(player, rules, index);
                    }
                }
            }
            else{
                croupier.askAction(player, rules, 0);
            }
            player.waitState();
            
        }
    }
    
    /**
     * verifie pour chacun des joueurs si il a gagne
     * @param croupier le croupier
     */
    public void checkPlayersWon(Croupier croupier) {
        for (Player player : playerList){
            player.playState();
            if (player.isPlayerSplit()){
                for (int index = 0; index < player.getPlayerHands().size(); index++){
                    rules.testVictory(croupier, player, index);
                }
            }
            else{
                rules.testVictory(croupier, player, 0);
            }
            
        }
    }
    
        /**
     * verifie si un des joueurs est en etat de blackjack
     * utilisation d'une copie de la liste
     * pour eviter les concurrent modification error
     */
    public void checkPlayersBlackjack() {
        for (Player player : new ArrayList<Player>(playerList)){
            if (rules.isBlackjackPlayer(player)){
                playerList.remove(player);
            }
        }
    }
    
    /**
     * verifie si un des joueurs a perdu
     * utilisation d'une copie de la liste
     * pour eviter les concurrent modification error
     */
    public void checkPlayersLose(){
        for (Player player : new ArrayList<Player>(playerList)){
            if (!player.isPlayerSplit()){
                if (rules.isLoserPlayer(player, 0)){
                    playerList.remove(player);
                }
            }

        }
    }

    /**
     * a la fin du tour, distribue les mises
     * @param croupier le croupier
     */
    public void conclusion(Croupier croupier) {
        for (Player player : playerList){
            if (player.isPlayerSplit()){
                for (int index = 0; index < player.getPlayerHands().size(); index++){
                    rules.calculBalance(croupier, player, index);
                }
            }
            else{
                rules.calculBalance(croupier, player, 0);
            } 
        }
    }

    public void setPlayers(ArrayList<Player> players) {
        playerList = players;
    }
    
    public Rules getRules(){
        return rules;
    }

    public Croupier getCroupier() {
        return croupier;
    }
    
}
