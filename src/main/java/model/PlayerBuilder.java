/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cartes.Paquet;
import controler.PlayerInput;

public class PlayerBuilder{
    
    public PlayerBuilder() {
    }
    
    /**
     * creation d'une IA
     * @param balance le solde a la creation
     * @param name le nom de l'IA
     * @return une IA
     */
    public Player createIAPlayer(int balance, String name){
        return new IAPlayer(balance, name);
    }
    
    /**
     * creation d'un humain
     * @param balance le solde a la creation
     * @param name le nom d'un humain
     * @param input le controler entre interface et action
     * @return un humain
     */
    public Player createHumanPlayer(int balance, String name, PlayerInput input){
        return new HumanPlayer(balance, name, input);
    }
    
    /**
     * creation d'un croupier
     * @param balance le solde a la creation
     * @param name le nom du croupier
     * @return un croupier
     */
    public Croupier createCroupier(int balance, String name){
        return new Croupier(balance, name);
    }

    
}
