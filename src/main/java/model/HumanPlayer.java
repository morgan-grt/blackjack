/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.PlayerInput;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HumanPlayer extends Player{
    
    private final PlayerInput input;    
    
    public HumanPlayer(int balance, String name, PlayerInput input) {
        super(balance, name);
        this.input = input;
    }

    /**
     * demande au joueur l'action qu'il veut effectuer
     */
    @Override
    public String action() {
        input.askAction();
        synchronized(this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return action;
    }

    /**
     * retourne le second type apres Player
     * @return le sedonc type
     */
    @Override
    public String myType() {
        return "HumanPlayer";
    }

    /**
     * demande au joueur de miser
     */
    @Override
    public int bet(){
        input.bet();
        //on attends le choix du joueur
        synchronized(this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return getBet(0);
    }

    /**
     * demande au joueur la valeur de son as
     * @param index index de la main choisie
     * @return l'assignement est fait
     */
    @Override
    public boolean setAsValue(int index) {
        input.askAsValue(index);
        synchronized(this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }        
}
