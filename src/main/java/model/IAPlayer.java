/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class IAPlayer extends Player{
    
    public IAPlayer(int balance, String name) {
        super(balance, name);
    }

    @Override
    public String action() {
        String action = "passe";
        if (getPoint(0) <= 16 && getPoint(0) >= 13){
            action ="tirer";
        }
        else if (getPoint(0) <= 12){
            action = "doubler";
        }
        return action;
    }

    @Override
    public int bet() {
        return balance / 10;
    }

    @Override
    public String myType() {
        return "IAPlayer";
    }

    /**
     * l'ia choisie 11 si la valeur de ses cartes
     * est inferieur ou egal a 10
     * @param index index de la main (0 pour l'ia)
     * @return l'assignement est fait
     */
    @Override
    public boolean setAsValue(int index) {
        if (getPoint(0) <= 10){
            playerHands.get(0).getAsValues().put(
                    playerHands.get(0).getAsValues().size(), 11);
        }
        else{
            playerHands.get(0).getAsValues().put(
                    playerHands.get(0).getAsValues().size(), 1);
        }
        return true;
    }
    
}
