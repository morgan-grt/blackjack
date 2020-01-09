/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.PlayerInput;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class VuePlayer extends JPanel{

    protected VuePlayerMise vueMise;
    protected VuePlayerAction vueAction;
    protected VuePlayerAsValue vueAs;
    protected GameGUI gui;
    protected PlayerInput input;

    public VuePlayer(GameGUI gui, PlayerInput input) {
        super();
        this.gui = gui;
        vueMise = new VuePlayerMise(0, input);
        vueAction = new VuePlayerAction(input);
        vueAs = new VuePlayerAsValue(input);
        this.input = input;
        
        setPreferredSize(new Dimension(300,100));
        setLayout(new BorderLayout());
        
        add(vueMise, BorderLayout.SOUTH);
        add(vueAction, BorderLayout.NORTH);
        add(vueAs, BorderLayout.CENTER);
        
        setOpaque(false);
        repaint();
        init();
    }
    
    public void init(){
        repaint();
    }

    public void addButtons() {
        vueMise.addButtons();
        gui.repaint();
        gui.setVisible(true);
    }

    public void removeButtons() {
        vueMise.removeButtons();
        gui.repaint();
    }

    public void addAsValue() {
        vueAs.addAsValue();
        gui.repaint();
        gui.setVisible(true);
    }

    public void removeAsValue() {
        vueAs.removeAsValue();
        gui.repaint();
    }

    public void addActions() {
        vueAction.addActions();
        gui.repaint();
        gui.setVisible(true);
    }

    public void removeActions() {
        vueAction.removeActions();
        gui.repaint();
    }

    public GameGUI getGUI(){
        return gui;
    }
    
}
