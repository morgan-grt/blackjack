/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class GameGUIPioche extends JFrame{
    protected VuePaquet pioche;
    
    public GameGUIPioche(){
        pioche = new VuePaquetVisible(this, "PIOCHE VISIBLE");
        this.setTitle("Pioche visible");
        this.setSize(720,240);
        add(pioche);
        //Positionnement au centre
        setLayout(new GridLayout(1,1));
        setLocationRelativeTo(null);
        setLocation(1080, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Visibilite     
        setVisible(true);
        repaint();
    }
    
    public VuePaquet getPioche() {
        return pioche;
    }
    
}
