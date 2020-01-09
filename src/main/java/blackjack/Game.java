/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import controler.Controler;
import controler.PlayerInput;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Croupier;
import model.Player;
import model.PlayerBuilder;
import view.GameGUI;
import view.GameGUIPioche;

/**
 * jeu principal
 */
public class Game {
    protected Rules rules;
    protected Controler controler;
    protected PlayerInput input;
    protected Player player, player2, player3, player4, player5;
    protected Croupier croupier;
    protected ArrayList<Player> players;
    protected GameGUI gui;
    protected GameGUIPioche gui2;
    
    public Game(){
        //creation des instances pour commencer le jeu
        rules = new Rules(2, 100);
        controler = new Controler(rules);
        input = new PlayerInput();
        
        //creation des joueurs et du croupier
        PlayerBuilder builder = new PlayerBuilder();
        player = builder.createHumanPlayer(100, "Robert", input);
        player2 = builder.createIAPlayer(100, "Jacques");
        player3 = builder.createIAPlayer(100, "Robert");
        player4 = builder.createIAPlayer(100, "Jacques");
        player5 = builder.createIAPlayer(100, "Robert");
        croupier = builder.createCroupier(0, "El Croupior");
        croupier.getPaquet().shuffle();
        input.setPlayer(player);
        
        //creation de l'interface graphique
        gui = new GameGUI(this, input);
        //gui2 = new GameGUIPioche(); //possibilite de voir la pioche
        //il faut aussi retirer le commentaire dans initView() 
        
        input.setVueMise(gui.getVuePlayer());
        
        //lancement du jeu
        init();
        play();
    }
    
    /**
     * initialisation du jeu
    **/
    public void init(){
        initModel();
        initView();  
    }
    
    /**
     * initialisation des modeles
     */
    public void initModel(){
        players = new ArrayList<Player>();
        controler.setPlayers(players);
        players.add(player);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        croupier.init();
        player.init();
        player2.init();
        player3.init();
        player4.init();
        player5.init();
    }
    
    /**
     * initilisation des vues
     */
    public void initView(){
        gui.getPioche().setPaquet(croupier.getPaquet());
        gui.getVuePaquetCroupier().setPaquet(croupier.getPlayerHands().get(0).getHandCart()); 
        gui.getVuePaquetPlayer1().setPaquet(player.getPlayerHands().get(0).getHandCart());
        gui.getVuePaquetPlayer2().setPaquet(player2.getPlayerHands().get(0).getHandCart());
        gui.getVuePaquetPlayer3().setPaquet(player3.getPlayerHands().get(0).getHandCart());
        gui.getVuePaquetPlayer4().setPaquet(player4.getPlayerHands().get(0).getHandCart());
        gui.getVuePaquetPlayer5().setPaquet(player5.getPlayerHands().get(0).getHandCart());
        //gui2.getPioche().setPaquet(croupier.getPaquet());
        gui.repaint();
    }
    
    /**
     * boucle de jeu
     * dans conclusion et a chaque distribution de carte il y a 
     * des sleep pour pouvoir admirer le jeu
     */
    public void play(){
        while(player.isPlaying()){
            bet();
            
            distribution();
            
            actionPlayers();
            
            actionCroupier();
            
            conclusion();
            
            nextTour();
        }
    }

    /**
     * demande aux joueurs de miser
     */
    public void bet() {
        controler.setBetPlayers();
    }
    
    /**
     * distribution des premieres cartes
     */
    public void distribution(){
        controler.distributeCartToPlayers(croupier);
        controler.distributeCartToCroupier(croupier);
        controler.distributeCartToPlayers(croupier);
    }
    
    /**
     * le controler demande a chaque joueur ce qu'il veut faire
     */
    public void actionPlayers(){
        controler.checkPlayersBlackjack();
        controler.action(croupier);
    }
    
    /**
     * le croupier tire ses propres cartes
     */
    public void actionCroupier(){
        controler.checkPlayersLose();
        while (croupier.getPoint(0) < 17){
            croupier.distributeCartToMySelf();
            rules.calculPoint(croupier, 0);
        }
    }
    
    /**
     * on verifie si des joueurs on gagne
     * on distribue les gains
     */
    public void conclusion(){
        controler.checkPlayersWon(croupier);
        controler.conclusion(croupier);
        //attente avant le debut du nouveau tour
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * initialisation pour le nouveau tour
     */
    public void nextTour(){
        init();
    }
}
