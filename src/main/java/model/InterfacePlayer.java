/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cartes.Paquet;

/**
 *declaration des methodes recquises pour les joueurs
 */
public interface InterfacePlayer {
    //etats possibles pour le joueur
    public enum State{
        PLAY,
        WAIT,
        STOP,
        ASWAIT,
        PLAYED,
    }
    
    public void waitState();
    public boolean isDoubleBetPossible(int index);
    public void stopState();
    public void playState();
    public void asWaitState();
    public void playedState();
    public boolean isValidBet(int bet);
    public Paquet getHandCart(int index);
    public void setHandCart(Paquet handCart, int index);
    public int getPoint(int index);
    public void setPoint(int point, int index);
    public int getBet(int index);
    public void setBet(int gameBet, int index);
    public int getBalance();
    public void setBalance(int balance);
    public State getState();
    public String action();
    public void stopPlaying();
}
