package view;

import blackjack.Game;
import controler.PlayerInput;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGUI extends JFrame{
    protected VuePaquetVisible  player1, player2, player3, player4, player5, croupier;
    protected VuePaquetCache pioche;
    protected VuePlayer vuePlayer;
    
    public GameGUI(Game game, PlayerInput input){
        super("BlackJack major");
        //Ferme la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1440,720));
        Container contentPane = getContentPane();

        PanelFond panelFond = new PanelFond(this);
        pioche = new VuePaquetCache(this, "Pioche");
        player1 = new VuePaquetVisible(this,  "Joueur 1");
        player2 = new VuePaquetVisible(this, "Joueur 2");
        player3 = new VuePaquetVisible(this, "Joueur 3");
        player4 = new VuePaquetVisible(this, "Joueur 4");
        player5 = new VuePaquetVisible(this, "Joueur 5");

        croupier = new VuePaquetVisible(this, "Croupier");
        
        vuePlayer = new VuePlayer(this, input);
               
        //Positionnement au centre
        setLocationRelativeTo(null);
        setLocation(0, 0);
        
        contentPane.add(panelFond);
        panelFond.setLayout(null);
        
        pioche.setBounds(10, 10, 300, 200);
        panelFond.add(pioche);
        
        croupier.setBounds(680, 50, 300, 200);
        panelFond.add(croupier);
        
        player1.setBounds(680, 420, 300, 250);
        panelFond.add(player1);
        
        player2.setBounds(920, 360, 300, 200);
        panelFond.add(player2);
        
        player3.setBounds(1200, 240, 300, 200);
        panelFond.add(player3);
        
        player4.setBounds(120, 240, 300, 200);
        panelFond.add(player4);
        
        player5.setBounds(420, 360, 300, 200);
        panelFond.add(player5);


        player1.add(vuePlayer, BorderLayout.SOUTH);
        
        pack();
        validate();
        repaint();
        //Visibilite     
        setVisible(true);
        
    }
    
    public void refresh(){
        repaint();
        revalidate();
    }

    public VuePaquet getPioche() {
        return pioche;
    }

    public VuePaquet getVuePaquetPlayer1() {
        return player1;
    }

    public VuePaquet getVuePaquetPlayer2() {
        return player2;
    }
    public VuePaquet getVuePaquetPlayer3() {
        return player3;
    }
    public VuePaquet getVuePaquetPlayer4() {
        return player4;
    }
    public VuePaquet getVuePaquetPlayer5() {
        return player5;
    }

    public VuePaquet getVuePaquetCroupier() {
        return croupier;
    }
    
    public VuePlayer getVuePlayer(){
        return vuePlayer;
    }
    
    
    /**
     * class pour l'image de fond et 
     * conteneur des vues de joueur
     */
    class PanelFond extends JPanel{
    private Image image;
    private GameGUI gui;
    public PanelFond(GameGUI gui){
        this.gui = gui;
        try {
            image = ImageIO.read(this.getClass().getResource("/sprites/fond_maj.png"));
        } catch (IOException ex) {
            Logger.getLogger(PanelFond.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(gui), image.getHeight(gui), null);
    }
}
    
}
