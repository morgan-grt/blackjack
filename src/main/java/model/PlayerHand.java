/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cartes.Paquet;
import java.util.HashMap;
import java.util.Map;

/**
 * represente la main d'un joueur
 */
public class PlayerHand {
    protected Paquet handCart;
    protected int point;
    protected int bet;
    protected Map<Integer, Integer> asValues;
    protected State state;
    
    public enum State{
        WAIT,
        LOSE,
        EQUALITY,
        WON,
        BLACKJACK,
        ASSURANCE,
        PLAYED,
    }

    public PlayerHand(Paquet handCart, int point, int bet) {
        this.handCart = handCart;
        this.point = point;
        this.bet = bet;
        asValues = new HashMap();
        state = State.WAIT;
    }

    public Paquet getHandCart() {
        return handCart;
    }

    public void setHandCart(Paquet handCart) {
        this.handCart = handCart;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Map<Integer, Integer> getAsValues() {
        return asValues;
    }

    public void setAsValues(Map<Integer, Integer> asValues) {
        this.asValues = asValues;
    }
    
    /**
     * mets la main en etat perdant
     */
    public void loseState() {
        state = State.LOSE;
    }

    /**
     * mets la main en etat egalite
     */
    public void equalityState() {
        state = State.EQUALITY;
    }

    /**
     * mets la main en etat gagnant
     */
    public void wonState() {
        state = State.WON;
    }
    
    /**
     * mets la main en etat de blackjack
     */
    public void blackjackState() {
        state = State.BLACKJACK;
    }
    
    /**
     * mets la main en etat d'assurance
     */
    public void assuranceState() {
        state = State.ASSURANCE;
    }
    
    /**
     * la main a ete joue
     */
    public void playedState() {
        state = State.PLAYED;
    }
    
    /**
     * mets la main en etat d'attente
     */
    public void waitState() {
        state = State.WAIT;
    }
    
    public State getState(){
        return state;
    }
}
