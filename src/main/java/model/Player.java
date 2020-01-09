/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cartes.Paquet;
import java.util.LinkedList;
import java.util.Map;
import observer.AbstractListenableModel;

/**
 * class abstraite pour les joueurs et le croupier
 */
public abstract class Player extends AbstractListenableModel implements InterfacePlayer{
    
    protected State state;
    protected LinkedList<PlayerHand> playerHands;
    protected int balance;
    protected String userName;
    protected boolean isPlaying;
    protected Map<Integer, Integer> asValues;
    protected boolean playerSplit;
    protected boolean playerDoubleBet;
    protected boolean isAssured;
    protected String action;
    
    public Player(int balance, String name){
        this.balance = balance;
        
        userName = name;
        isPlaying = true;
        init();
    }

    /**
     * fait jouer le joueur
     * @return retourne l'action
     */
    public abstract String action();

    /**
     * mets le joeur en etat d'attente
     */
    public void waitState() {
        state = State.WAIT;
    }
    
    /**
     * mets le joeur en etat d'attente
     * si il a un as
     */
    public void asWaitState() {
        state = State.ASWAIT;
    }

    /**
     * verifie si le joueur peut doubler sa mise
     * @param index index de la main choisie
     * @return le joueur peut doubler
     */
    public boolean isDoubleBetPossible(int index) {
        return (state == State.PLAY && playerHands.get(index).handCart.getPaquet().size() < 3 
                && (balance >= playerHands.get(index).bet));
    }

    /**
     * verifie si le joueur peut spliter son jeu
     * @return le joueur peut spliter
     */
    public boolean isSplitPossible(){
        boolean possible = playerHands.get(0).getHandCart().getThis(0).getValeur()
                .equals(playerHands.get(0).getHandCart().getThis(1));
        return (possible && balance >= playerHands.get(0).bet);
    }

    /**
     * mets le joueur en etat d'arret
     */
    public void stopState() {
        state = State.STOP;
    }

    /**
     * mets le joueur en etat de jeu
     */
    public void playState(){
        state = State.PLAY;
    }
    
    /**
     * mets le joueur en etat a joue
     */
    public void playedState(){
        state = State.PLAYED;
    }


    /**
     * verifie si une mise est valide
     * @param bet la mise
     * @return la mise est valide
     */
    public boolean isValidBet(int bet) {
        return (balance > 0 && balance >= bet);
    }
    
    /**
     * le joueur arrete de jouer
     */
    public void stopPlaying(){
        isPlaying = true;
    }

    /**
     *
     * @param index index de la main choisie
     * @return la main choisie
     */
    public Paquet getHandCart(int index) {
        return playerHands.get(index).handCart;
    }

    /**
     *
     * @param handCart retourne la main choisie
     * @param index index de la main choisie
     */
    public void setHandCart(Paquet handCart, int index) {
        playerHands.get(index).setHandCart(handCart);
    }

    /**
     *
     * @param index index de la main choisie
     * @return retourne le nombre de point de la main choisie
     */
    public int getPoint(int index) {
        return playerHands.get(index).point;
    }

    /**
     *
     * @param point les points actuels de la main
     * @param index index de la main choisie
     */
    public void setPoint(int point, int index) {
        playerHands.get(index).setPoint(point);
        fireChange();
    }
    
    /**
     *
     * @param index index de la main choisie
     * @return la mise de la main
     */
    public int getBet(int index) {
        return playerHands.get(index).bet;
    }
    
    /**
     *
     * @param bet mise du joueur
     * @param index index de la main choisie
     */
    public void setBet(int bet, int index) {
        playerHands.get(index).setBet(bet);
        fireChange();
    }
    
    /**
     * demande au joueur de miser
     * @return retourne la mise
     */
    public abstract int bet();

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
        fireChange();
    }

    public State getState() {
        return state;
    }

    public String getUserName() {
        return userName;
    }
    
    public boolean isPlaying(){
        return isPlaying;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public abstract String myType();

    public abstract boolean setAsValue(int index);
    
    public boolean isPlayerSplit(){
        return playerSplit;
    }
    
    public boolean isPlayerDoubleBet(){
        return playerDoubleBet;
    }
    
    public void playerSplit(){
        playerSplit = true;
    }
    
    public void playerDoubleBet(){
        playerDoubleBet = true;
    }
    
    /**
     * initialise le joueur
     */
    public void init() {
        playerHands = new LinkedList();
        PlayerHand playerHand = new PlayerHand(new Paquet(), 0, 0);
        playerHands.add(playerHand);
        state = State.WAIT;     
        playerSplit = false;
        playerDoubleBet = false;
        isAssured = false;
        fireChange();
    }

    /**
     * retourne pour la main choisie ses as
     * @param index index de la main choisie
     * @return les as de la main choisie
     */
    public Map<Integer, Integer> getAsValues(int index) {
        return playerHands.get(index).getAsValues();
    }

    public LinkedList<PlayerHand> getPlayerHands() {
        return playerHands;
    }

    /**
     * verifie si le joueur peut faire une assurance
     * @return le joueur peut faire une assurance
     */
    public boolean isAssurancePossible() {
        return (balance > (getBet(0) + getBet(0) / 2));
    }

    /**
     * le player devient assure
     */
    public void setAssured() {
        isAssured = true;
    }
    
    public boolean isAssured(){
        return isAssured;
    }
    
    public void setAction(String action){
        this.action = action;
    }
}
