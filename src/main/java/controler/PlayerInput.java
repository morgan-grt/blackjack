/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.Player;
import view.VuePlayer;

/**
 *
 */
public class PlayerInput{
    
    protected Player player;
    protected VuePlayer vuePlayer;
    private int index;
        
    public PlayerInput() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * recupere la mise du joueur
     * on attend que le joueur est valide sa mise
     */
    public void bet() {
        vuePlayer.init();
        vuePlayer.addButtons();
    }
    
    /**
     * modifie la mise du joueur selon
     * les appelles de la vue
     * @param ajout ajout un nombre de jetons
     * @param index index de la main
     */
    public void setBet(int ajout, int index){
        if (ajout == 0){
            player.setBet(0, index);
        }
        else{
            player.setBet(player.getBet(0) + ajout, index);
        }
    }
    
    /**
     * reveil le jeu lorsque que le joueur a mise
     */
    public void callBackBet(){
        synchronized(player){
            player.notify(); //fin de l'attente du choix
        }
    }
    
    public VuePlayer getVuePlayer(){
        return vuePlayer;
    }

    public void setVueMise(VuePlayer vuePlayer) {
        this.vuePlayer = vuePlayer;
    }

    /**
     * demande la valeur de l'as
     * @param index index de la main
     */
    public void askAsValue(int index){
        this.index = index;
        vuePlayer.init();
        vuePlayer.addAsValue();
    }
    
    /**
     * recupere la valeur de l'as
     * @param valueString valeur de l'as en string
     */
    public void callBackAsValue(String valueString){
        vuePlayer.removeAsValue();
        int value = 1;
        if (valueString.equals("11")){
            value = 11;
        }
        if (value != 1 && value != 11){
            player.getPlayerHands().get(index).getAsValues().put(
                    player.getPlayerHands().get(index).getAsValues().size(), 1);
        }
        else{
            player.getPlayerHands().get(index).getAsValues().put(
                    player.getPlayerHands().get(index).getAsValues().size(), value);
        }
        synchronized(player){
            player.notify(); //fin de l'attente du choix
        }
    }

    public void askAction() {
        vuePlayer.init();
        vuePlayer.addActions();
    }
    
    public void callBackAction(String action){
        vuePlayer.removeActions();
        player.setAction(action);
        synchronized(player){
            player.notify(); //fin de l'attente du choix
        }
    }
}
